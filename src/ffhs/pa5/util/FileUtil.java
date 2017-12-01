package ffhs.pa5.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Provides some helper functions for files.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class FileUtil {

    /**
     * Checks if the given path exists
     *
     * @param path the path
     * @return true if the file exists
     */
    public static boolean exists(String path) {
        File f = new File(path);

        return f.isFile();
    }

    /**
     * Converts a string path to a Path instance
     *
     * @param path the string path
     * @return the Path instance
     */
    private static Path getPath(String path) {
        return Paths.get(path).toAbsolutePath();
    }

    /**
     * Reads the content of a file
     *
     * @param path the path
     * @return null on error, the file content on success
     */
    public static String read(String path) {
        if (!exists(path)) {
            return null;
        }

        try {
            Path filePath = getPath(path);

            return new String(Files.readAllBytes(filePath));
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return null;
        }
    }

    /**
     * Writes the content to a file
     *
     * @param path    the file path
     * @param content the content to write
     * @return true on successfull read, false on error
     */
    public static boolean write(String path, String content) {
        try {
            Path filePath = getPath(path);

            Files.write(filePath, content.getBytes());

            return true;
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return false;
        }
    }

    /**
     * Deletes a file
     *
     * @param path the file path
     * @return true on successfull delete, false on error
     */
    public static boolean deleteIfExists(String path) {
        try {
            Path filePath = getPath(path);

            Files.deleteIfExists(filePath);

            return true;
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return false;
        }
    }
}
