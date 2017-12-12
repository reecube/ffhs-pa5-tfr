package ffhs.pa5.view;

import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.LanguageKeys;
import ffhs.pa5.util.ResourceBundleUtil;
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
     * TODO @Barbara: javadoc
     */
    static void showErrorAgendaItemNull() {
        showError(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemNull));
    }

    static void showErrorParticipantNull() {
        showError(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_participantNull));
    }

    static void showErrorMoveAgendaItemUp() {
        showError(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemMoveUp));
    }

    static void showErrorMoveAgendaItemDown() {
        showError(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemMoveDown));
    }

    private static void showError(String contentText) { //TODO: Language key mitgeben
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.messageType_error));
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    //TODO: private static void ShowAlert(AlertType)
}
