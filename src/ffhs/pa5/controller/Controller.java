package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.model.DataFile;
import ffhs.pa5.util.Logger;
import ffhs.pa5.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * This is the main controller class.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Controller {

    private Observer viewObserver;

    private FileStorageFactory fileStorageFactory;

    // ********************************************************************************
    // fxml components
    // ********************************************************************************
    // TODO


    // ********************************************************************************
    // main code
    // ********************************************************************************

    private void initializeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.FXML_MAIN));
        Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

        View view = fxmlLoader.getController();
        this.viewObserver = view;

        primaryScene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

        view.setTitle(Constants.VIEW_TITLE);
        view.setScene(primaryScene);
        view.sizeToScene();
        view.show();
    }

    private void initializeFileStorageFactory() {
        // TODO: check for arguments and open file from path if there is one => try catch this one

        this.fileStorageFactory = new FileStorageFactory();
        fileStorageFactory.open();

        DataFile file = fileStorageFactory.getFile();
        file.addObserver(viewObserver);
        file.updateView();
    }

    public Controller() throws IOException {

        initializeView();
        initializeFileStorageFactory();
    }
}
