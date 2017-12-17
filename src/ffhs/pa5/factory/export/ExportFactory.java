package ffhs.pa5.factory.export;

import ffhs.pa5.factory.archive.ArchiveFactoryEntry;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.model.DataFile;
import ffhs.pa5.util.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */

public class ExportFactory {

    private ExportModel exportModel;
    private ExportOutputHandler exportOutputHandler;

    /**
     * TODO
     *
     * @param exportModel         TODO
     * @param exportOutputHandler TODO
     */
    public ExportFactory(ExportModel exportModel, ExportOutputHandler exportOutputHandler) {
        this.exportModel = exportModel;
        this.exportOutputHandler = exportOutputHandler;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public static ExportOutputHandler[] getAvailableHandlers() {
        return new ExportOutputHandler[]{
                new TextExportOutputHandler(),
        };
    }

    /**
     * TODO
     *
     * @return true on success, false on error
     */
    public boolean export(String path) {
        if (path == null || !(exportOutputHandler instanceof FileExportOutputHandler)) {
            return exportOutputHandler.export(exportModel);
        }

        FileExportOutputHandler fileExportOutputHandler = (FileExportOutputHandler) exportOutputHandler;
        fileExportOutputHandler.setPath(path);
        return fileExportOutputHandler.export(exportModel);

    }
}
