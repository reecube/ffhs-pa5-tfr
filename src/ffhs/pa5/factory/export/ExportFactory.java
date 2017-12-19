package ffhs.pa5.factory.export;

/**
 * Export factory defines the model an the handler
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
     * Defines the export model
     *
     * @param exportModel         exportModel
     * @param exportOutputHandler exportOutputHandler
     */
    public ExportFactory(ExportModel exportModel, ExportOutputHandler exportOutputHandler) {
        this.exportModel = exportModel;
        this.exportOutputHandler = exportOutputHandler;
    }

    /**
     * Choose the export output handler
     *
     * @return ExportOutputHandler
     */
    public static ExportOutputHandler[] getAvailableHandlers() {
        return new ExportOutputHandler[]{
                new TextExportOutputHandler(),
        };
    }

    /**
     * Exporting the SPV-file
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
