package ffhs.pa5.tests;

import ffhs.pa5.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

/**
 * Tests for the FileUtil class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class FileUtilTest extends TestingBase {

    @Test
    public void testFileLifecycle() throws IOException {
        // Config variables
        final String fileName = "test.txt";
        final String expectedContent = "Lorem ipsum";

        // Clean test files
        Files.deleteIfExists(new File(fileName).toPath());

        logTestCase("Exists (false)");
        assertFalse(FileUtil.exists(fileName));

        logTestCase("Read (null)");
        assertNull(FileUtil.read(fileName));

        logTestCase("Write");
        assertTrue(FileUtil.write(fileName, expectedContent));

        logTestCase("Exists (true)");
        assertTrue(FileUtil.exists(fileName));

        logTestCase("Read");
        final String content = FileUtil.read(fileName);
        assertNotNull(content);
        assertEquals(expectedContent, content);

        logTestCase("Delete");
        assertTrue(FileUtil.delete(fileName));

        logTestCase("Exists (false)");
        assertFalse(FileUtil.exists(fileName));
    }
}
