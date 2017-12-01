package ffhs.pa5.model.util;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public enum FileStorageFactoryResult {

    SUCCESS,
    ERROR_UNKNOWN,
    ERROR_UNEXPECTED_BEHAVIOR,
    ERROR_FILE_NOT_FOUND,
    ERROR_FILE_INVALID,
    ERROR_FILE_LOCKED,
    ERROR_LOCKSTATE_FAILED,
    ERROR_BUILD_ARCHIVE,
}