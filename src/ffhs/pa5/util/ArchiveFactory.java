package ffhs.pa5.util;

import ffhs.pa5.model.util.ArchiveFactoryEntry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ArchiveFactory {

    private String path;

    public ArchiveFactory(String path) {
        this.path = path;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public ArchiveFactoryEntry[] read() {
        return read(true);
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public ArchiveFactoryEntry[] read(boolean printStackTrace) {
        try {
            final ZipFile zipFile = new ZipFile(path);
            final Enumeration<? extends ZipEntry> enu = zipFile.entries();
            final ArrayList<ArchiveFactoryEntry> entries = new ArrayList<>();
            while (enu.hasMoreElements()) {
                final ZipEntry ze = enu.nextElement();

                try {
                    final InputStream is = zipFile.getInputStream(ze);

                    final byte[] data = new byte[is.available()];
                    final int result = is.read(data);

                    if (result <= 0) {
                        continue;
                    }

                    entries.add(new ArchiveFactoryEntry(ze, new String(data)));
                } catch (Exception ex) {
                    if (printStackTrace) {
                        ex.printStackTrace();
                    }
                }
            }

            return entries.toArray(new ArchiveFactoryEntry[0]);
        } catch (Exception ex) {
            if (printStackTrace) {
                ex.printStackTrace();
            }

            return new ArchiveFactoryEntry[0];
        }
    }

    /**
     * TODO
     *
     * @param entries TODO
     * @return true on success, false on error
     */
    public boolean write(ArchiveFactoryEntry[] entries) {
        return write(entries, true);
    }

    /**
     * TODO
     *
     * @param entries         TODO
     * @param printStackTrace TODO
     * @return true on success, false on error
     */
    public boolean write(ArchiveFactoryEntry[] entries, boolean printStackTrace) {
        try {
            final File f = new File(path);
            final ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(f));

            for (ArchiveFactoryEntry entry : entries) {
                final ZipEntry ze = new ZipEntry(entry.getPath());
                zos.putNextEntry(ze);
                zos.write(entry.getContent().getBytes());
                zos.closeEntry();
            }

            zos.close();

            return true;
        } catch (Exception ex) {
            if (printStackTrace) {
                ex.printStackTrace();
            }

            return false;
        }
    }
}