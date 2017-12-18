package ffhs.pa5.factory.archive;

import java.io.File;
import java.util.zip.ZipEntry;

/**
 * The Archive factory entry defines the entry, path and content
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ArchiveFactoryEntry {

    private String directory;
    private String file;
    private String content;

    /**
     * Define the archive factory entry
     *
     * @param entry entry
     * @param content content
     */
    public ArchiveFactoryEntry(ZipEntry entry, String content) {
        final String path = entry.getName();
        final int lastIndex = path.lastIndexOf(File.separatorChar);
        if (lastIndex < 0) {
            this.directory = null;
            this.file = path;
        } else {
            this.directory = path.substring(0, lastIndex);
            this.file = path.substring(lastIndex + 1);
        }
        this.content = content;
    }

    /**
     * The factory entry for archive
     *
     * @param directory directory
     * @param file file
     * @param content content
     */
    public ArchiveFactoryEntry(String directory, String file, String content) {
        this.directory = directory;
        this.file = file;
        this.content = content;
    }

    /**
     * Returns the path by file
     *
     * @return file
     */
    public String getPath() {
        if (directory == null) {
            return file;
        }

        return directory + File.separatorChar + file;
    }

    public String getContent() {
        return content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getPath() + " [" + content.length() + "]";
    }
}
