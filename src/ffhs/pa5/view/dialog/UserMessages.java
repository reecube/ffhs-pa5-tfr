package ffhs.pa5.view.dialog;

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

    public static void showMessageAgendaItemNull(){
        //TODO: Use variables for translations
        showErrorMessage("The agenda item you are looking for is null");
    }

    public static void showMessageParticipantNull(){
        //TODO: Use variables for translations
        showErrorMessage("The participant you are looking for is null");
    }

    public static void showMessageMoveAgendaItemUp(){
        //TODO: Use variables for translations
        showErrorMessage("You can't move the agenda item up");
    }

    public static void showMessageMoveAgendaItemDown(){
        //TODO: Use variables for translations
        showErrorMessage("You can't move the agenda item down");
    }

    private static void showErrorMessage(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
