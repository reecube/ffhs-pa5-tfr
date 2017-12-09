package ffhs.pa5.controller;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

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
    // TODO


    // ********************************************************************************
    // main code
    // ********************************************************************************


    public void setViewObserver(Observer viewObserver) {
        this.viewObserver = viewObserver;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
    }
}