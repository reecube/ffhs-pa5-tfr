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
public class TextExportOutputHandler extends FileExportOutputHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public FileChooser.ExtensionFilter[] getFileExtension() {
        return new FileChooser.ExtensionFilter[]{
                new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt")
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] getContent(ExportModel model) {
        return new byte[0];
        //TODO: ev anderer Rückgabewert
        //hier kommt dann der text
    }

}
