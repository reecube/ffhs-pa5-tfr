package ffhs.pa5.tests;

import ffhs.pa5.Constants;
import ffhs.pa5.model.util.FileStorageFactoryResult;
import ffhs.pa5.util.FileStorageFactory;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the FileStorageFactory class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class FileStorageFactoryTest extends TestingBase {

    /**
     * Tests the FileStorageFactory lifecycle of an file.
     */
    @Test
    public void testLifecycle() throws Exception {
        // Config variables
        final String storageFile = "test.spv";
        final String storageLockFile = storageFile + Constants.DATA_FILE_LOCK_EXTENSION;
        final Path pathStorageFile = new File(storageFile).toPath();
        final Path pathStorageLockFile = new File(storageLockFile).toPath();

        // Clean test files
        Files.deleteIfExists(pathStorageFile);
        Files.deleteIfExists(pathStorageLockFile);

        // Variable declaration
        final FileStorageFactory fileStorageFactory = new FileStorageFactory();

        logTestCase("Open file (not found)");
        assertEquals(FileStorageFactoryResult.ERROR_FILE_NOT_FOUND, fileStorageFactory.open(storageFile));
        assertFalse(Files.exists(pathStorageFile));
        assertFalse(Files.exists(pathStorageLockFile));

        logTestCase("Create new File");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.open());
        assertFalse(Files.exists(pathStorageFile));
        assertFalse(Files.exists(pathStorageLockFile));

        logTestCase("Save file");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.save(storageFile));
        assertTrue(Files.exists(pathStorageFile));
        assertTrue(Files.exists(pathStorageLockFile));

        logTestCase("Close file");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.close(storageFile, false));
        assertTrue(Files.exists(pathStorageFile));
        assertFalse(Files.exists(pathStorageLockFile));

        logTestCase("Open file (unlocked)");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.open(storageFile));
        assertTrue(Files.exists(pathStorageFile));
        assertTrue(Files.exists(pathStorageLockFile));

        logTestCase("Open file (locked)");
        assertEquals(FileStorageFactoryResult.ERROR_FILE_LOCKED, fileStorageFactory.open(storageFile));
        assertTrue(Files.exists(pathStorageFile));
        assertTrue(Files.exists(pathStorageLockFile));

        // Clean test files
        fileStorageFactory.close(storageFile, false);
        Files.deleteIfExists(pathStorageFile);
        Files.deleteIfExists(pathStorageLockFile);
    }
}