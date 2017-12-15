package ffhs.pa5.factory.storage;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.archive.ArchiveFactory;
import ffhs.pa5.model.*;
import ffhs.pa5.factory.archive.ArchiveFactoryEntry;
import ffhs.pa5.util.FileUtil;
import ffhs.pa5.util.JsonUtil;
import ffhs.pa5.util.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class FileStorageFactory {

    private HashMap<String, ArchiveFactoryEntry> files;
    private DataFile file;

    /**
     * TODO
     */
    public FileStorageFactory() {
        this.files = null;
        this.file = null;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    private boolean isInitialized() {
        return files != null && file != null;
    }

    /**
     * TODO
     *
     * @param path TODO
     * @return TODO
     */
    private String getLockFilePath(String path) {
        return path + Constants.DATA_FILE_LOCK_EXTENSION;
    }

    /**
     * TODO
     *
     * @param path TODO
     * @return TODO
     */
    private boolean isLocked(String path) {
        return FileUtil.exists(getLockFilePath(path));
    }

    /**
     * TODO
     *
     * @param path           TODO
     * @param newStateLocked TODO
     * @return TODO
     */
    private boolean setLocked(String path, boolean newStateLocked) {
        final String lockFilePath = getLockFilePath(path);

        // Check if the the locked state is already correct
        if (newStateLocked == isLocked(path)) {
            return true;
        }

        // Delete the lock file if it should not be locked anymore
        if (!newStateLocked) {
            return FileUtil.deleteIfExists(lockFilePath);
        }

        User user = User.getInstance();

        // Write the current user to the lock file if it should be locked
        return FileUtil.write(lockFilePath, user.toString());
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public FileStorageFactoryResult open() {
        this.file = new DataFile("");
        this.files = new HashMap<>();

        return FileStorageFactoryResult.SUCCESS;
    }

    /**
     * TODO
     *
     * @param entries TODO
     * @return TODO
     */
    private static HashMap<String, ArchiveFactoryEntry> getArchiveFactoryEntryMap(ArchiveFactoryEntry[] entries) {
        HashMap<String, ArchiveFactoryEntry> result = new HashMap<>();

        for (ArchiveFactoryEntry entry : entries) {
            result.put(entry.getPath(), entry);
        }

        return result;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    private FileStorageFactoryResult checkApplicationVersion() {
        if (file == null) {
            return FileStorageFactoryResult.ERROR_UNEXPECTED_BEHAVIOR;
        }

        if (file.getMetadata().getAppVersion() != Constants.APP_VERSION) {
            return FileStorageFactoryResult.ERROR_FILE_VERSION_MISMATCH;
        }

        return FileStorageFactoryResult.SUCCESS;
    }

    /**
     * TODO
     *
     * @param entryArray TODO
     * @return TODO
     */
    private FileStorageFactoryResult parseArchiveFactoryEntriesToFile(ArchiveFactoryEntry[] entryArray) {
        HashMap<String, ArchiveFactoryEntry> entries = getArchiveFactoryEntryMap(entryArray);

        ArchiveFactoryEntry dataJsonFile = entries.get(Constants.DATA_FILE_PATH);

        if (dataJsonFile == null) {
            return FileStorageFactoryResult.ERROR_FILE_INVALID;
        }

        entries.remove(Constants.DATA_FILE_PATH);

        // Initialize
        JsonUtil jsonUtil = new JsonUtil();
        this.file = jsonUtil.parse(dataJsonFile.getContent(), DataFile.class);
        this.files = entries;

        return checkApplicationVersion();
    }

    /**
     * TODO
     *
     * @param path TODO
     * @return TODO
     */
    public FileStorageFactoryResult open(String path) {
        if (!FileUtil.exists(path)) {
            return FileStorageFactoryResult.ERROR_FILE_NOT_FOUND;
        }

        if (isLocked(path)) {
            return FileStorageFactoryResult.ERROR_FILE_LOCKED;
        }

        if (!setLocked(path, true)) {
            return FileStorageFactoryResult.ERROR_LOCKSTATE_FAILED;
        }

        try {
            ArchiveFactory archiveFactory = new ArchiveFactory(path);

            ArchiveFactoryEntry[] entries = archiveFactory.read();

            return parseArchiveFactoryEntriesToFile(entries);
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return FileStorageFactoryResult.ERROR_UNKNOWN;
        }
    }

    /**
     * TODO
     *
     * @param path TODO
     * @return TODO
     */
    public FileStorageFactoryResult save(String path) {
        return save(path, false);
    }

    /**
     * TODO
     *
     * @param path       TODO
     * @param ignoreLock TODO
     * @return TODO
     */
    public FileStorageFactoryResult save(String path, boolean ignoreLock) {
        if (!isInitialized()) {
            return FileStorageFactoryResult.ERROR_UNINITIALIZED;
        }

        if (!ignoreLock && isLocked(path)) {
            return FileStorageFactoryResult.ERROR_FILE_LOCKED;
        }

        if (!setLocked(path, true)) {
            return FileStorageFactoryResult.ERROR_LOCKSTATE_FAILED;
        }


        JsonUtil jsonUtil = new JsonUtil();
        String jsonContent = jsonUtil.stringify(file);
        ArchiveFactoryEntry dataEntry = new ArchiveFactoryEntry(null, Constants.DATA_FILE_PATH, jsonContent);

        if (files.containsKey(dataEntry.getPath())) {
            return FileStorageFactoryResult.ERROR_UNEXPECTED_BEHAVIOR;
        }

        ArrayList<ArchiveFactoryEntry> tmpFiles = new ArrayList<>(files.values());
        tmpFiles.add(dataEntry);

        ArchiveFactory archiveFactory = new ArchiveFactory(path);

        if (!archiveFactory.write(tmpFiles.toArray(new ArchiveFactoryEntry[0]))) {
            return FileStorageFactoryResult.ERROR_BUILD_ARCHIVE;
        }

        file.getMetadata().setSaveDate(new Date());

        return FileStorageFactoryResult.SUCCESS;
    }

    /**
     * TODO
     *
     * @param path TODO
     * @param save TODO
     * @return TODO
     */
    public FileStorageFactoryResult close(String path, boolean save) {
        if (!isInitialized()) {
            return FileStorageFactoryResult.ERROR_UNINITIALIZED;
        }

        if (save) {
            FileStorageFactoryResult saveResult = save(path, true);

            if (saveResult != FileStorageFactoryResult.SUCCESS) {
                return saveResult;
            }
        }

        if (!setLocked(path, false)) {
            return FileStorageFactoryResult.ERROR_LOCKSTATE_FAILED;
        }

        this.files = null;
        this.file = null;

        return FileStorageFactoryResult.SUCCESS;
    }

    public DataFile getFile() {
        return file;
    }
}
