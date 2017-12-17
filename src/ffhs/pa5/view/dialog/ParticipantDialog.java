package ffhs.pa5.view.dialog;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.model.Participant;
import ffhs.pa5.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The participant dialog for add and edit more informations
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ParticipantDialog extends Dialog<Participant> implements Initializable {

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

    @FXML
    private Button buttonSave;


    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * Handling the participant dialog by getting a new instance and returns null
     *
     * @return TODO
     */
    public static ParticipantDialog getNewInstance() {
        return getNewInstance(null);
    }

    /**
     * Handling the participant dialog by getting a new instance
     *
     * @param participant TODO
     * @return TODO
     */
    public static ParticipantDialog getNewInstance(Participant participant) {
        try {
            ResourceBundle bundle = Controller.getBundle();
            URL location = ParticipantDialog.class.getResource(Constants.FXML_DIALOG_PARTICIPANT);
            FXMLLoader fxmlLoader = new FXMLLoader(location, bundle);

            Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

            ParticipantDialog instance = fxmlLoader.getController();

            if (participant == null) {
                participant = new Participant();
            }

            instance.loadParticipant(participant);

            DialogPane dialogPane = instance.getDialogPane();

            Window window = dialogPane.getScene().getWindow();
            window.setOnCloseRequest(event -> instance.hide());

            instance.setTitle(bundle.getString(LanguageKey.VIEW_TITLE_PARTICIPANT.name()));

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
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputFirstname.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
        inputLastname.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
        inputEmail.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
        inputPhone.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
        inputRole.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
        inputNotes.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
    }

    /**
     * Refresh the view aftes save informations from participant
     */
    private void refreshView() {
        buttonSave.setDisable(inputFirstname.getText().length() == 0
                && inputLastname.getText().length() == 0
                && inputEmail.getText().length() == 0
                && inputPhone.getText().length() == 0
                && inputRole.getText().length() == 0
                && inputNotes.getText().length() == 0);
    }

    /**
     * Load informations about participant
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
     * Handling the save button and that action
     */
    @FXML
    private void handleButtonSaveAction() {
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
     * Handling the cancel button and that action
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
