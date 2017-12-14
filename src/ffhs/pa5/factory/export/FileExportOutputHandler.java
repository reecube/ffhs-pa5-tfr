package ffhs.pa5.factory.export;

import ffhs.pa5.util.Logger;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
    public boolean export(ExportModel content) {
        byte[] fileContent = getContent(content);

        try {
            // @see https://examples.javacodegeeks.com/core-java/nio/file-nio/java-nio-write-file-example/
            // @see http://tutorials.jenkov.com/java-nio/files.html
            Files.write(Paths.get(path), fileContent);

            return true;
        } catch (IOException ex) {
            Logger logger = Logger.getInstance();
            logger.handleException(ex);
        }

        return false;
    }

    /**
     * TODO
     *
     * @return TODO
     */
    protected abstract byte[] getContent(ExportModel model);
}
