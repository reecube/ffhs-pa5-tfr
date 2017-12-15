package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.model.*;
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
        fxmlLoader.setResources(getBundle());
        Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

        View view = fxmlLoader.getController();
        view.setController(this);
        this.viewObserver = view;

        primaryScene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

        view.setTitle(Constants.VIEW_TITLE); //TODO @Barbara: mit label_viewTitleMain ersetzen, das geht im..
        //TODO @Barbara ..Moment aber nicht, darauf warten, dass der Singleton gemacht ist
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
        file.addObserverRecursive(viewObserver);
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

    /**
     * TODO
     *
     * @return TODO
     */
    public static ResourceBundle getBundle() {
        if (bundle != null) {
            return bundle;
        }

        String userLanguage = defineUserLanguage();
        Locale locale = new Locale(userLanguage);
        bundle = ResourceBundle.getBundle(Constants.PACKAGE_TRANSLATION, locale);

        return bundle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAgendaItem(AgendaItem agendaItem) {
        return fileStorageFactory.getFile().getMeeting().addAgendaItem(agendaItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean editAgendaItem(AgendaItem agendaItem) {
        return fileStorageFactory.getFile().getMeeting().editAgendaItem(agendaItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAgendaItem(AgendaItem agendaItem) {
        return fileStorageFactory.getFile().getMeeting().removeAgendaItem(agendaItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean moveAgendaItem(AgendaItem agendaItem, boolean moveUp) {
        return fileStorageFactory.getFile().getMeeting().moveAgendaItem(agendaItem, moveUp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addParticipant(Participant participant) {
        return fileStorageFactory.getFile().getMeeting().addParticipant(participant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean editParticipant(Participant participant) {
        return fileStorageFactory.getFile().getMeeting().editParticipant(participant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeParticipant(Participant participant) {
        return fileStorageFactory.getFile().getMeeting().removeParticipant(participant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeMeeting() {
        fileStorageFactory.getFile().getMeeting().setState(State.CLOSED);
    }

    /**
     * TODO
     *
     * @return TODO
     */
    private static String defineUserLanguage() {
        String language = Locale.getDefault().getLanguage();

        if (Arrays.asList(Constants.SUPPORTED_LANGUAGES).contains(language)) {
            return language;
        }

        return Constants.DEFAULT_LANGUAGE;
    }
}
