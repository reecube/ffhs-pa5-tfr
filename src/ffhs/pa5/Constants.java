package ffhs.pa5;

import java.util.Locale;

/**
 * This class contains all constants.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class Constants {

    // app
    public static final int APP_VERSION = 1;
    public static final String APP_FILEEXTENSION_SPV = "spv";
    public static final String APP_FILEEXTENSION_SPV_TEMPLATE = "spvm";

    // data file
    public static final String DATA_FILE_LOCK_EXTENSION = ".lock";
    public static final String DATA_FILE_PATH = "data.json";

    // path
    private static final String PATH_RESOURCES = "/ffhs/pa5/resources";
    private static final String PATH_RESOURCES_DIALOG = PATH_RESOURCES + "/dialog";

    // fxml
    public static final String FXML_MAIN = PATH_RESOURCES + "/application.fxml";
    public static final String FXML_DIALOG_PARTICIPANT = PATH_RESOURCES_DIALOG + "/participant.fxml";
    public static final String FXML_DIALOG_AGENDA_ITEM = PATH_RESOURCES_DIALOG + "/agendaItem.fxml";

    // translation
    public static final String PACKAGE_TRANSLATION = "ffhs.pa5.resources.application";
    public static final String[] SUPPORTED_LANGUAGES = new String[]{
            Locale.GERMAN.getLanguage(),
            Locale.ENGLISH.getLanguage(),
    };
    public static final String DEFAULT_LANGUAGE = Locale.ENGLISH.getLanguage();

    // view
    public static final int VIEW_WIDTH = 800;
    public static final int VIEW_HEIGHT = 600;

    // ResourceUtil
    public static final String INDICATOR_MISSING_RESOURCE = "Error: missing resource";
    public static final String INDICATOR_MISSING_KEY = "Error: missing key";

    // export
    public static final String JAVA_LINE_SEPARATOR = "line.separator";
    public static final String JAVA_USER_HOME = "user.home";
    public static final char CHAR_TAB = '\t';
}
