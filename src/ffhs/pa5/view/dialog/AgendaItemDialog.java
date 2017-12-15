package ffhs.pa5.view.dialog;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class AgendaItemDialog extends Dialog<AgendaItem> {

    private AgendaItem agendaItem;


    // ********************************************************************************
    // fxml components
    // ********************************************************************************

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputTitle;


    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * TODO
     *
     * @return TODO
     */
    public static AgendaItemDialog getNewInstance() {
        return getNewInstance(null);
    }

    /**
     * TODO
     *
     * @param agendaItem TODO
     * @return TODO
     */
    public static AgendaItemDialog getNewInstance(AgendaItem agendaItem) {
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

            DialogPane dialogPane = instance.getDialogPane();

            Window window = dialogPane.getScene().getWindow();
            window.setOnCloseRequest(event -> instance.hide());

            instance.setTitle(bundle.getString(LanguageKey.VIEW_TITLE_AGENDAITEM.name()));

            primaryScene.getStylesheets().add(AgendaItemDialog.class.getResource(Constants.CSS_MAIN).toExternalForm());

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
     * @param agendaItem TODO
     */
    private void loadAgendaItem(AgendaItem agendaItem) {
        this.agendaItem = agendaItem;

        inputId.setText(agendaItem.getId());
        inputTitle.setText(agendaItem.getTitle());
    }

    /**
     * TODO
     *
     * @param event TODO
     */
    @FXML
    private void handleButtonSaveAction(ActionEvent event) {
        // TODO: Check if the id is not empty!

        agendaItem.setId(inputId.getText());
        agendaItem.setTitle(inputTitle.getText());

        setResult(agendaItem);
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
