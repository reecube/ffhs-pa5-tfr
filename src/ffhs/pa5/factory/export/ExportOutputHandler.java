package ffhs.pa5.factory.export;

/**
 * Define Export output handler
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public interface ExportOutputHandler {

    /**
     * Declare export variable
     *
     * @param exportModel exportModel
     */
    boolean export(ExportModel exportModel);
}
