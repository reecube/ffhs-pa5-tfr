package ffhs.pa5.tests;

import ffhs.pa5.model.util.FileStorageFactoryResult;
import ffhs.pa5.util.FileStorageFactory;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;

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

        // Clean test files
        Files.deleteIfExists(new File(storageFile).toPath());

        // Variable declaration
        FileStorageFactory fileStorageFactory;

        logTestCase("Create new File");
        fileStorageFactory = new FileStorageFactory();
        assertTrue(fileStorageFactory.open());

        logTestCase("Write file");
        fileStorageFactory = new FileStorageFactory();
        assertTrue(fileStorageFactory.close(storageFile, true) == FileStorageFactoryResult.SUCCESS);

        logTestCase("Open file");
        fileStorageFactory = new FileStorageFactory();
        assertTrue(fileStorageFactory.open(storageFile) == FileStorageFactoryResult.SUCCESS);

        fileStorageFactory.close(storageFile, false);

        // Clean test files
        Files.deleteIfExists(new File(storageFile).toPath());
    }
}