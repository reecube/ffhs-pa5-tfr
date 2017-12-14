package ffhs.pa5.view;

import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.LanguageKeys;
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
     */
    static void showErrorAgendaItemNull() {
        showError(ResourceUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemNull));
    }

    /**
     * TODO
     */
    static void showErrorParticipantNull() {
        showError(ResourceUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_participantNull));
    }

    /**
     * TODO
     */
    static void showErrorMoveAgendaItemUp() {
        showError(ResourceUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemMoveUp));
    }

    /**
     * TODO
     */
    static void showErrorMoveAgendaItemDown() {
        showError(ResourceUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemMoveDown));
    }

    private static void showError(String contentText) { //TODO: Language key mitgeben
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceUtil.getLangString(Controller.getBundle(), LanguageKeys.messageType_error));
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    //TODO: private static void ShowAlert(AlertType)
}
