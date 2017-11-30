package ffhs.pa5.tests;

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
public class FileFileStorageFactoryTest extends TestingBase {

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
        fileStorageFactory.newFile();

        logTestCase("Write file");
        fileStorageFactory = new FileStorageFactory();
        assertTrue(fileStorageFactory.writeFile(storageFile));

        logTestCase("Open file");
        fileStorageFactory = new FileStorageFactory();
        assertTrue(fileStorageFactory.writeFile(storageFile));

        // Clean test files
        Files.deleteIfExists(new File(storageFile).toPath());
    }
}