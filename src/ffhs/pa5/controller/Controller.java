package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.factory.storage.FileStorageFactoryResult;
import ffhs.pa5.model.*;
import ffhs.pa5.util.DateUtil;
import ffhs.pa5.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.time.LocalDate;
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
        ResourceBundle bundle = getBundle();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.FXML_MAIN), bundle);
        Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

        View view = fxmlLoader.getController();
        view.setController(this);
        this.viewObserver = view;

        primaryScene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

        view.setTitle(bundle.getString(LanguageKey.VIEW_TITLE_MAIN.name()));
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
    public boolean changeState(State newState) {
        final Meeting meeting = fileStorageFactory.getFile().getMeeting();
        final State oldState = meeting.getState();

        if (oldState == State.CLOSED || oldState == newState) {
            return false;
        }

        meeting.setState(newState);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeMeetingTitle(String newValue) {
        fileStorageFactory.getFile().getMeeting().setTitle(newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeMeetingDate(LocalDate newValue) {
        fileStorageFactory.getFile().getMeeting().setDate(DateUtil.fromLocalDate(newValue));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeMeetingLocation(String newValue) {
        fileStorageFactory.getFile().getMeeting().setLocation(newValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileStorageFactoryResult openFile(String path) {
        return fileStorageFactory.open(path);
    }

    @Override
    public FileStorageFactoryResult saveFile(String path) {
        return fileStorageFactory.save(path);
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
        changeState(State.CLOSED);
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
