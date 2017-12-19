package ffhs.pa5.view;

import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.scene.control.Alert;

/**
 * Handling the alerts
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
abstract class AlertHelper {

    /**
     * See the specific error with that alert
     *
     * @param contentText    contentText
     * @param additionalInfo If you want to add some more information to the occured error, use this String. If not, use null.
     */
    static void showError(LanguageKey contentText, String additionalInfo) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_TITLE));
        alert.setHeaderText(null);
        if (additionalInfo == null) {
            alert.setContentText(ResourceUtil.getLangString(Controller.getBundle(), contentText));
        } else {
            alert.setContentText(ResourceUtil.getLangString(Controller.getBundle(), contentText) + ": " +
                    additionalInfo);
        }
        alert.showAndWait();
    }

    /**
     * See the specific warning with that alert
     *
     * @param contentText the context text
     */
    static void showWarning(LanguageKey contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.WARNING_TITLE));
        alert.setHeaderText(null);
        alert.setContentText(ResourceUtil.getLangString(Controller.getBundle(), contentText));
        alert.showAndWait();
    }
}
