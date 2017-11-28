package ffhs.pa5.controller;

import ffhs.pa5.Constants;
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
public class Controller implements Observer, Initializable {

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

            // fancy new line of code as an example

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * The constructor of the controller.
     */
    public Controller() {
        // TODO: game.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
}