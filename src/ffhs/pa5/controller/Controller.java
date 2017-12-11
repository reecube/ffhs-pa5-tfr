package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.model.DataFile;
import ffhs.pa5.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

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
public class Controller implements ViewController {

    private Observer viewObserver;

    private FileStorageFactory fileStorageFactory;

    // ********************************************************************************
    // fxml components
    // ********************************************************************************
    // TODO


    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * TODO
     *
     * @throws IOException TODO
     */
    private void initializeView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.FXML_MAIN));
        Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

        View view = fxmlLoader.getController();
        view.setController(this);
        this.viewObserver = view;

        primaryScene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

        view.setTitle(Constants.VIEW_TITLE);
        view.setScene(primaryScene);
        view.sizeToScene();
        view.show();
    }

    /**
     * TODO
     */
    private void initializeFileStorageFactory() {
        // TODO: check for arguments and open file from path if there is one => try catch this one

        this.fileStorageFactory = new FileStorageFactory();
        fileStorageFactory.open();

        DataFile file = fileStorageFactory.getFile();
        file.addObserver(viewObserver);
        file.updateView();
    }

    /**
     * TODO
     *
     * @throws IOException TODO
     */
    public Controller() throws IOException {

        initializeView();
        initializeFileStorageFactory();
    }
}
