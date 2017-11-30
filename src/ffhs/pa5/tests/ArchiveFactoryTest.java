package ffhs.pa5.tests;

import ffhs.pa5.model.util.ArchiveFactoryEntry;
import ffhs.pa5.util.ArchiveFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.zip.ZipEntry;

import static org.junit.Assert.*;

/**
 * Tests for the ArchiveFactory class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ArchiveFactoryTest extends TestingBase {

    /**
     * Tests the ArchiveFactoryEntry class.
     */
    @Test
    public void testArchiveFactoryEntry() {

        // Config variables
        final String directory = "sample-dir";
        final String file = "sample.zip";
        final String content = "lorem ipsum";
        final String path = directory + File.separatorChar + file;

        // Variable declaration
        ArchiveFactoryEntry archiveFactoryEntry;

        logTestCase("String input only");
        archiveFactoryEntry = new ArchiveFactoryEntry(directory, file, content);
        assertEquals(archiveFactoryEntry.getPath(), path);
        assertEquals(archiveFactoryEntry.getContent(), content);

        logTestCase("ZipEntry and String input");
        ZipEntry zipEntry = new ZipEntry(path);
        archiveFactoryEntry = new ArchiveFactoryEntry(zipEntry, content);
        assertEquals(archiveFactoryEntry.getPath(), path);
        assertEquals(archiveFactoryEntry.getContent(), content);
    }

    /**
     * Tests the ArchiveFactory lifecycle of an archive file.
     *
     * @throws IOException the exception
     */
    @Test
    public void testLifecycle() throws IOException {

        // Config variables
        final String archiveFile = "test.zip";
        final ArrayList<ArchiveFactoryEntry> entries = new ArrayList<>();
        entries.add(new ArchiveFactoryEntry("dir1", "file1.txt", "Test11"));
        entries.add(new ArchiveFactoryEntry("dir1", "file2.txt", "Test12"));
        entries.add(new ArchiveFactoryEntry("dir2", "file1.txt", "Test21"));
        final ArchiveFactoryEntry[] content = entries.toArray(new ArchiveFactoryEntry[0]);

        // Clean test files
        Files.deleteIfExists(new File(archiveFile).toPath());

        // Variable declaration
        ArchiveFactory archiveFactory;
        ArchiveFactoryEntry[] result;

        logTestCase("Write file");
        archiveFactory = new ArchiveFactory(archiveFile);
        assertTrue(archiveFactory.write(content));

        logTestCase("Read file");
        archiveFactory = new ArchiveFactory(archiveFile);
        result = archiveFactory.read();
        assertEquals(result.length, content.length);
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i].getPath(), content[i].getPath());
            assertEquals(result[i].getContent(), content[i].getContent());
        }
    }
}