package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.DataFile;
import ffhs.pa5.model.Participant;
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
    private static ResourceBundle bundle;

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
        fxmlLoader.setResources(defineResourceBundle());
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
        bundle = defineResourceBundle();
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public static ResourceBundle defineResourceBundle() {
        String userLanguage = defineUserLanguage();
        Locale locale = new Locale(userLanguage);
        return ResourceBundle.getBundle(Constants.PACKAGE_TRANSLATION, locale);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addAgendaItem(AgendaItem agendaItem) {
        // TODO: implement this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editAgendaItem(AgendaItem agendaItem) {
        // TODO: implement this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeAgendaItem(AgendaItem agendaItem) {
        // TODO: implement this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean moveAgendaItem(AgendaItem agendaItem, int value) {
        // TODO: implement this
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addParticipant(Participant participant) {
        // TODO: implement this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editParticipant(Participant participant) {
        // TODO: implement this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeParticipant(Participant participant) {
        // TODO: implement this
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeMeeting() {
        // TODO: implement this
    }

    /**
     * TODO
     *
     * @return TODO
     */
    private static String defineUserLanguage() {
        if (Locale.getDefault().getLanguage().equals("de")) {
            return "de";
        } else {
            return "en";
        }
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }
}
