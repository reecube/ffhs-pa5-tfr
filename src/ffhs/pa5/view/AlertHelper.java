package ffhs.pa5.view;

import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.scene.control.Alert;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
abstract class AlertHelper {

    /**
     * TODO
     *
     * @param contentText TODO
     */
    static void showError(LanguageKey contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_TITLE));
        alert.setHeaderText(null);
        alert.setContentText(ResourceUtil.getLangString(Controller.getBundle(), contentText));
        alert.showAndWait();
    }
}
