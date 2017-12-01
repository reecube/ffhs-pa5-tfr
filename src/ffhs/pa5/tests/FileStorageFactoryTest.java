package ffhs.pa5.tests;

import ffhs.pa5.model.util.FileStorageFactoryResult;
import ffhs.pa5.util.FileStorageFactory;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

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

        // Clean test files
        Files.deleteIfExists(new File(storageFile).toPath());

        // Variable declaration
        final FileStorageFactory fileStorageFactory = new FileStorageFactory();

        logTestCase("Open file (not found)");
        assertEquals(FileStorageFactoryResult.ERROR_FILE_NOT_FOUND, fileStorageFactory.open(storageFile));

        logTestCase("Create new File");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.open());

        logTestCase("Save file");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.save(storageFile));

        logTestCase("Close file");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.close(storageFile, false));

        logTestCase("Open file (unlocked)");
        assertEquals(FileStorageFactoryResult.SUCCESS, fileStorageFactory.open(storageFile));

        logTestCase("Open file (locked)");
        assertEquals(FileStorageFactoryResult.ERROR_FILE_LOCKED, fileStorageFactory.open(storageFile));

        // Clean test files
        fileStorageFactory.close(storageFile, false);
        Files.deleteIfExists(new File(storageFile).toPath());
    }
}