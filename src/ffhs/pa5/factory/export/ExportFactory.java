package ffhs.pa5.factory.export;

import ffhs.pa5.factory.archive.ArchiveFactoryEntry;
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
    //TODO

    private ExportModel exportModel;//TODO Wir hatten mal von einem Datafile gesprochen, aber das wäre jetzt vermutlich eher das Model, oder?
    private ExportOutputHandler exportOutputHandler;
    private String path; //TODO du hattest einmal gesagt, den Pfad bekomme ich über das DataFile. Das DataFile hat aber gar keine Variable path?

    /**
     * TODO
     *
     * @param exportModel         TODO
     * @param exportOutputHandler TODO
     * @param path                TODO
     */
    public ExportFactory(ExportModel exportModel, ExportOutputHandler exportOutputHandler, String path) {
        this.exportModel = exportModel;
        this.exportOutputHandler = exportOutputHandler;
        this.path = path;
    }

    /**
     * TODO
     *
     * @return true on success, false on error
     */
    public boolean write() {
        try {
            final File file = new File(path);
            final FileOutputStream fos = new FileOutputStream(file);

            // TODO: Keine Ahnung, welcher String da umgewandelt werden müsste ;)
            //fos.write(exportOutputHandler.export(exportModel).getBytes());
            fos.close();

            return true;
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return false;
        }
    }
}
