package ffhs.pa5.factory.export;

import javafx.stage.FileChooser;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */

public abstract class FileExportOutputHandler implements ExportOutputHandler {

    private String path;

    /**
     * TODO
     */
    FileExportOutputHandler() {
        this.path = null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public abstract FileChooser.ExtensionFilter[] getFileExtension();

    /**
     * {@inheritDoc}
     */
    @Override
    public void export(ExportModel content) {
        //TODO
    }
}
