package ffhs.pa5.view.dialog;

import ffhs.pa5.Constants;
import ffhs.pa5.model.Participant;
import ffhs.pa5.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ParticipantDialog extends Dialog<Participant> {

    private Participant participant;

    // ********************************************************************************
    // fxml components
    // ********************************************************************************

    @FXML
    private TextField inputFirstname;

    @FXML
    private TextField inputLastname;

    @FXML
    private TextField inputEmail;

    @FXML
    private TextField inputPhone;

    @FXML
    private TextField inputRole;

    @FXML
    private TextArea inputNotes;

    /**
     * TODO
     *
     * @return TODO
     */
    public static ParticipantDialog getNewInstance() {
        return getNewInstance(null);
    }

    /**
     * TODO
     *
     * @param participant TODO
     * @return TODO
     */
    public static ParticipantDialog getNewInstance(Participant participant) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ParticipantDialog.class.getResource(Constants.FXML_DIALOG_PARTICIPANT));
            Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

            ParticipantDialog instance = fxmlLoader.getController();

            if (participant == null) {
                participant = new Participant();
            }

            instance.loadParticipant(participant);

            DialogPane dialogPane = instance.getDialogPane();

            Window window = dialogPane.getScene().getWindow();
            window.setOnCloseRequest(event -> instance.hide());

            instance.setTitle(Constants.VIEW_TITLE);

            primaryScene.getStylesheets().add(ParticipantDialog.class.getResource(Constants.CSS_MAIN).toExternalForm());

            dialogPane.setContent(primaryScene.getRoot());

            return instance;
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return null;
        }
    }

    /**
     * TODO
     *
     * @param participant TODO
     */
    private void loadParticipant(Participant participant) {
        this.participant = participant;

        inputFirstname.setText(participant.getFirstname());
        inputLastname.setText(participant.getLastname());
        inputEmail.setText(participant.getEmail());
        inputPhone.setText(participant.getPhone());
        inputRole.setText(participant.getRole());
        inputNotes.setText(participant.getNotes());
    }

    /**
     * TODO
     *
     * @param event TODO
     */
    @FXML
    private void handleButtonSaveAction(ActionEvent event) {
        participant.setFirstname(inputFirstname.getText());
        participant.setLastname(inputLastname.getText());
        participant.setEmail(inputEmail.getText());
        participant.setPhone(inputPhone.getText());
        participant.setRole(inputRole.getText());
        participant.setNotes(inputNotes.getText());

        setResult(participant);
        hide();
    }

    /**
     * TODO
     *
     * @param event TODO
     */
    @FXML
    private void handleButtonCancelAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
