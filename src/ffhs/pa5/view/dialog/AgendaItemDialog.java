package ffhs.pa5.view.dialog;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.type.LanguageKey;
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
 * Define the agenda item dialog
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class AgendaItemDialog extends Dialog<AgendaItem> implements Initializable {

    private AgendaItem agendaItem;


    // ********************************************************************************
    // fxml components
    // ********************************************************************************

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputTitle;

    @FXML
    private Button buttonSave;


    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * Get a new instance from agenda item dialog
     *
     * @return getNewInstance
     */
    public static AgendaItemDialog getNewInstance() {
        return getNewInstance(null, false);
    }

    /**
     * Return a news instance from agenda item dialog if agenda item is true
     *
     * @param agendaItem agendaItem
     * @return getNewInstance
     */
    public static AgendaItemDialog getNewInstance(AgendaItem agendaItem) {
        return getNewInstance(agendaItem, true);
    }

    /**
     * Handling with agenda item dialog
     *
     * @param agendaItem agendaItem
     * @return instance
     */
    private static AgendaItemDialog getNewInstance(AgendaItem agendaItem, boolean disableId) {
        try {
            ResourceBundle bundle = Controller.getBundle();
            URL location = AgendaItemDialog.class.getResource(Constants.FXML_DIALOG_AGENDA_ITEM);
            FXMLLoader fxmlLoader = new FXMLLoader(location, bundle);
            Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

            AgendaItemDialog instance = fxmlLoader.getController();

            if (agendaItem == null) {
                agendaItem = new AgendaItem();
            }

            instance.loadAgendaItem(agendaItem);

            if (disableId) {
                instance.disableInputId();
            }

            DialogPane dialogPane = instance.getDialogPane();

            Window window = dialogPane.getScene().getWindow();
            window.setOnCloseRequest(event -> instance.hide());

            instance.setTitle(bundle.getString(LanguageKey.VIEW_TITLE_AGENDAITEM.name()));

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
        inputId.textProperty().addListener((ov, oldValue, newValue) -> refreshView());

        inputTitle.textProperty().addListener((ov, oldValue, newValue) -> refreshView());
    }

    /**
     * Refresh the spv-view
     */
    private void refreshView() {
        buttonSave.setDisable(inputId.getText().length() == 0 || inputTitle.getText().length() == 0);
    }

    /**
     * Load agenda item
     *
     * @param agendaItem agendaItem
     */
    private void loadAgendaItem(AgendaItem agendaItem) {
        this.agendaItem = agendaItem;

        inputId.setText(agendaItem.getId());
        inputTitle.setText(agendaItem.getTitle());
    }

    /**
     * Disable input ID
     */
    private void disableInputId() {
        inputId.setDisable(true);
    }

    /**
     * Handling the save button and that action
     */
    @FXML
    private void handleButtonSaveAction() {
        agendaItem.setId(inputId.getText());
        agendaItem.setTitle(inputTitle.getText());

        setResult(agendaItem);
        hide();
    }

    /**
     * Handling the cancel button and that action
     *
     * @param event event
     */
    @FXML
    private void handleButtonCancelAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
