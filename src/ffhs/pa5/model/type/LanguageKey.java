package ffhs.pa5.model.type;

/**
 * Declare the language key
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public enum LanguageKey {

    // Errors
    ERROR_TITLE,
    ERROR_PARTICIPANT_NULL,
    ERROR_PARTICIPANT_ADD,
    ERROR_PARTICIPANT_EDIT,
    ERROR_PARTICIPANT_REMOVE,
    ERROR_AGENDAITEM_NULL,
    ERROR_AGENDAITEM_ADD,
    ERROR_AGENDAITEM_EDIT,
    ERROR_AGENDAITEM_REMOVE,
    ERROR_AGENDAITEM_MOVE,
    ERROR_EXPORT,
    ERROR_STORAGE,
    ERROR_HANDLER_NULL,
    ERROR_UNKNOWN,
    ERROR_UNINITIALIZED,
    ERROR_UNEXPECTED_BEHAVIOR,
    ERROR_FILE_NOT_FOUND,
    ERROR_FILE_INVALID,
    ERROR_FILE_LOCKED,
    ERROR_FILE_VERSION_MISMATCH,
    ERROR_LOCKSTATE_FAILED,
    ERROR_BUILD_ARCHIVE,

    // Warnings
    WARNING_TITLE,
    WARNING_CHANGE_TAB_REVERSE,

    // View
    VIEW_TITLE_MAIN,
    VIEW_TITLE_PARTICIPANT,
    VIEW_TITLE_AGENDAITEM,
    VIEW_FILEFILTER_ALL_DESCRIPTION,
    VIEW_FILEFILTER_FILE_DESCRIPTION,
    VIEW_FILEFILTER_TEMPLATE_DESCRIPTION,

    // Export
    EXPORT_HEADER_DATE,
    EXPORT_HEADER_CREATION,
    EXPORT_HEADER_LAST_EDITION,
    EXPORT_MEETING_DATE,
    EXPORT_PARTICIPANTS,
    EXPORT_AGENDAITEMS,
    EXPORT_NEXT_MEETING
}
