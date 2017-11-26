package ffhs.pa5.tests;

import ffhs.pa5.model.util.ArchiveFactoryEntry;
import ffhs.pa5.util.ArchiveFactory;
import org.junit.Test;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ArchiveFactoryTest {

    /**
     * TODO
     *
     * @throws Exception the exception
     */
    @Test
    public void testEverything() throws Exception {
        // TODO: improve this
        ArchiveFactory archiveFactory = new ArchiveFactory("test.zip");
        ArrayList<ArchiveFactoryEntry> entries = new ArrayList<>();

        entries.add(new ArchiveFactoryEntry("dir1", "file1.txt", "Test"));

        archiveFactory.write(entries.toArray(new ArchiveFactoryEntry[0]), true);

        ArchiveFactoryEntry[] result = archiveFactory.read(true);

        for (ArchiveFactoryEntry entry:result) {
            System.out.println(entry);
        }
    }
}