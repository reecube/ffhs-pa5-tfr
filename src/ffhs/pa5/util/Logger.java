package ffhs.pa5.util;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Logger {

    private static Logger instance = null;

    /**
     * Exists only to defeat instantiation.
     */
    private Logger() {
    }

    /**
     * Singleton
     *
     * @return new instance or the existing one if one is existing
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void handleException(Exception ex) {
        ex.printStackTrace();
    }
}