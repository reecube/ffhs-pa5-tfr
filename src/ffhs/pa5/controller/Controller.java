package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.util.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * This is the main controller class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Controller implements Initializable {

    private Observer viewObserver;

    // view stuff
    private Stage stage;

    // ********************************************************************************
    // fxml components
    // ********************************************************************************

    @FXML
    @SuppressWarnings("unused")
    private BorderPane main;

    /**
     * shows the different strategies
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings({"unchecked", "unused"})
    public void handleStart(ActionEvent event) {
        try {

            Node node = (Node) event.getSource();
            this.stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_MAIN));
            loader.setController(this);
            this.main = loader.load();
            Scene scene = new Scene(main, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
            scene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());
            stage.setScene(scene);

            // TODO

            stage.show();
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);
        }
    }


    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * The constructor of the controller.
     */
    public Controller(Observer viewObserver) {
        this.viewObserver = viewObserver;

        // TODO: model.addObserver(viewObserver)
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
}