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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("The agenda item you are looking for is null");
        alert.showAndWait();
    }

    public static void showMessageParticipantNull(){
        //TODO: Use variables for translations
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("The participant you are looking for is null");
        alert.showAndWait();
    }

    public static void showMessageMoveAgendaItemUp(){
        //TODO: Use variables for translations
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("You can't move the agenda item up");
        alert.showAndWait();
    }

    public static void showMessageMoveAgendaItemDown(){
        //TODO: Use variables for translations
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("You can't move the agenda item down");
        alert.showAndWait();
    }
}
