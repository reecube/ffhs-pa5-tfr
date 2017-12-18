package ffhs.pa5.view;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Helper for choosing file
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class FileChooserHelper {

    /**
     * Get the filename from meeting title
     *
     * @param meetingTitle meetingTitle
     * @return meetingTitle
     */
    public static String getFileNameFromMeetingTitle(String meetingTitle, String extension) {
        meetingTitle = meetingTitle.replaceAll("[\\s]", "_");

        return meetingTitle.replaceAll("[^A-Za-z0-9]", "") + "." + extension.toLowerCase();
    }

    /**
     * Get the file chooser
     *
     * @return fileChooser
     */
    private static FileChooser getFileChooser(final String initialDirectory, final String initialFileName) {
        ResourceBundle bundle = Controller.getBundle();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(ResourceUtil.getLangString(bundle, LanguageKey.VIEW_TITLE_MAIN));
        fileChooser.setInitialDirectory(new File(initialDirectory));
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
     * Show the open dialog
     *
     * @param ownerWindow ownerWindow
     */
    static File showOpenDialog(final String initialDirectory, final Window ownerWindow) {
        FileChooser fileChooser = getFileChooser(initialDirectory, null);

        return fileChooser.showOpenDialog(ownerWindow);
    }

    /**
     * Show save dialog
     *
     * @param ownerWindow ownerWindow
     */
    static File showSaveDialog(final String initialDirectory, final String initialFileName, final Window ownerWindow) {
        FileChooser fileChooser = getFileChooser(initialDirectory, initialFileName);

        return fileChooser.showSaveDialog(ownerWindow);
    }

    /**
     * Show export dialog
     *
     * @param initialDirectory initialDirectory
     * @param fileExtensions   fileExtensions
     */
    public static File showExportDialog(
            final String initialDirectory,
            final FileChooser.ExtensionFilter[] fileExtensions,
            final Window ownerWindow
    ) {
        FileChooser fileChooser = getFileChooser(initialDirectory, null);

        ObservableList<FileChooser.ExtensionFilter> extensionFilters = fileChooser.getExtensionFilters();
        extensionFilters.clear();
        extensionFilters.addAll(fileExtensions);

        return fileChooser.showSaveDialog(ownerWindow);
    }
}
