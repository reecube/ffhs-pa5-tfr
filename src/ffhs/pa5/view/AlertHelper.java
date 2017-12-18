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
     * @param contentText contentText
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
}
