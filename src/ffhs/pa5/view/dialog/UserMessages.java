package ffhs.pa5.view.dialog;

import ffhs.pa5.controller.Controller;
import ffhs.pa5.resources.lang.LanguageKeys;
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

public class UserMessages {

    public static void showMessageAgendaItemNull() {
        showErrorMessage(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemNull));
    }

    public static void showMessageParticipantNull() {
        showErrorMessage(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_participantNull));
    }

    public static void showMessageMoveAgendaItemUp() {
        showErrorMessage(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemMoveUp));
    }

    public static void showMessageMoveAgendaItemDown() {
        showErrorMessage(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.userMessage_agendaItemMoveDown));
    }

    private static void showErrorMessage(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(ResourceBundleUtil.getLangString(Controller.getBundle(), LanguageKeys.messageType_error));
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
