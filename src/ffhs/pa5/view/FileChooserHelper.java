package ffhs.pa5.view;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class FileChooserHelper {

    /**
     * TODO
     *
     * @param meetingTitle TODO
     * @return TODO
     */
    public static String getFileNameFromMeetingTitle(String meetingTitle, String extension) {
        meetingTitle = meetingTitle.replaceAll("[\\s]", "_");

        return meetingTitle.replaceAll("[^A-Za-z0-9]", "") + "." + extension.toLowerCase();
    }

    /**
     * TODO
     *
     * @return TODO
     */
    private static FileChooser getFileChooser(String initialFileName) {
        ResourceBundle bundle = Controller.getBundle();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(ResourceUtil.getLangString(bundle, LanguageKey.VIEW_TITLE_MAIN));
        fileChooser.setInitialDirectory(
                new File(System.getProperty(Constants.JAVA_USER_HOME))
        );
        fileChooser.setInitialFileName(initialFileName);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                ResourceUtil.getLangString(bundle, LanguageKey.VIEW_FILEFILTER_ALL_DESCRIPTION),
                "*." + Constants.APP_FILEEXTENSION_SPV,
                "*." + Constants.APP_FILEEXTENSION_SPV_TEMPLATE
        ), new FileChooser.ExtensionFilter(
                ResourceUtil.getLangString(bundle, LanguageKey.VIEW_FILEFILTER_FILE_DESCRIPTION),
                "*." + Constants.APP_FILEEXTENSION_SPV
        ), new FileChooser.ExtensionFilter(
                ResourceUtil.getLangString(bundle, LanguageKey.VIEW_FILEFILTER_TEMPLATE_DESCRIPTION),
                "*." + Constants.APP_FILEEXTENSION_SPV_TEMPLATE
        ));

        return fileChooser;
    }

    /**
     * TODO
     *
     * @param ownerWindow TODO
     */
    static File showOpenDialog(final String initialFileName, final Window ownerWindow) {
        FileChooser fileChooser = getFileChooser(initialFileName);

        return fileChooser.showOpenDialog(ownerWindow);
    }

    /**
     * TODO
     *
     * @param ownerWindow TODO
     */
    static File showSaveDialog(final String initialFileName, final Window ownerWindow) {
        FileChooser fileChooser = getFileChooser(initialFileName);

        return fileChooser.showSaveDialog(ownerWindow);
    }
}
