package ffhs.pa5.util;

import ffhs.pa5.Constants;
import ffhs.pa5.model.*;
import ffhs.pa5.model.util.ArchiveFactoryEntry;
import ffhs.pa5.model.util.FileStorageFactoryResult;

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

    public FileStorageFactory() {
        this.files = null;
        this.file = null;
    }

    private boolean isInitialized() {
        return files == null || file == null;
    }

    private boolean isLocked(String path) {
        return FileUtil.exists(path + Constants.DATA_FILE_LOCK_EXTENSION);
    }

    private boolean setLocked(String path, boolean newStateLocked) {
        if (!newStateLocked) {
            return FileUtil.delete(path);
        }

        User user = User.getInstance();

        return FileUtil.write(path + Constants.DATA_FILE_LOCK_EXTENSION, user.toString());
    }

    public boolean open() {
        this.file = new DataFile();

        return true;
    }

    private static HashMap<String, ArchiveFactoryEntry> getArchiveFactoryEntryMap(ArchiveFactoryEntry[] entries) {
        HashMap<String, ArchiveFactoryEntry> result = new HashMap<>();

        for (ArchiveFactoryEntry entry : entries) {
            result.put(entry.getPath(), entry);
        }

        return result;
    }

    private FileStorageFactoryResult parseArchiveFactoryEntriesToFile(ArchiveFactoryEntry[] entryArray) {
        HashMap<String, ArchiveFactoryEntry> entries = getArchiveFactoryEntryMap(entryArray);

        ArchiveFactoryEntry dataJsonFile = entries.get(Constants.DATA_FILE_PATH);

        if (dataJsonFile == null) {
            return FileStorageFactoryResult.ERROR_FILE_INVALID;
        }

        entries.remove(Constants.DATA_FILE_PATH);

        // Initialize
        this.file = JsonUtil.parse(dataJsonFile.getContent(), DataFile.class);
        this.files = entries;

        return FileStorageFactoryResult.SUCCESS;
    }

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
            Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return FileStorageFactoryResult.ERROR_UNKNOWN;
        }
    }

    public FileStorageFactoryResult close(String path, boolean write) throws Exception {
        if (!isInitialized()) {
            throw new Exception("The file has not been initialized yet!");
        }

        if (write) {
            // TODO: implement this
            throw new Exception("Not implemented yet!");
        }

        if (!setLocked(path, false)) {
            return FileStorageFactoryResult.ERROR_LOCKSTATE_FAILED;
        }

        this.files = null;
        this.file = null;

        return FileStorageFactoryResult.SUCCESS;
    }


}
