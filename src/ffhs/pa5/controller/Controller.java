package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.factory.export.ExportFactory;
import ffhs.pa5.factory.export.ExportModel;
import ffhs.pa5.factory.export.ExportOutputHandler;
import ffhs.pa5.factory.storage.FileStorageFactory;
import ffhs.pa5.factory.storage.FileStorageFactoryResult;
import ffhs.pa5.model.*;
import ffhs.pa5.model.type.*;
import ffhs.pa5.util.DateUtil;
import ffhs.pa5.util.Logger;
import ffhs.pa5.view.View;
import ffhs.pa5.view.ViewObservable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
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

    private String lastSavePath;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLastSavePath() {
        return lastSavePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeMeetingNextMeeting(LocalDate newValue) {
        fileStorageFactory.getFile().getMeeting().setNextMeeting(DateUtil.fromLocalDate(newValue));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean export(ExportOutputHandler handler, String path) {
        Date creationDate;
        Date lastEditionDate;
        if (lastSavePath != null) {
            try {
                BasicFileAttributes attr = Files.readAttributes(Paths.get(lastSavePath), BasicFileAttributes.class);
                creationDate = new Date(attr.creationTime().toMillis());
                lastEditionDate = new Date(attr.lastModifiedTime().toMillis());
            } catch (IOException ex) {
                final Logger logger = Logger.getInstance();
                logger.handleException(ex);

                creationDate = null;
                lastEditionDate = null;
            }
        } else {
            creationDate = new Date();
            lastEditionDate = new Date();
        }

        ExportModel model = new ExportModel(fileStorageFactory.getFile(), creationDate, lastEditionDate);
        ExportFactory factory = new ExportFactory(model, handler);
        return factory.export(path);
    }

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

    private void updateView() {
        DataFile file = fileStorageFactory.getFile();
        ViewObservable viewObservable = new ViewObservable(file);
        viewObservable.addObserver(viewObserver);
        file.updateView();
    }

    /**
     * TODO
     */
    private void initializeFileStorageFactory(String path) {
        this.fileStorageFactory = new FileStorageFactory();
        this.lastSavePath = path;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (fileStorageFactory == null) {
                return;
            }

            fileStorageFactory.close(lastSavePath, false);
        }));

        FileStorageFactoryResult result = openFile(path);

        if (result == FileStorageFactoryResult.SUCCESS) {
            return;
        }

        System.err.println(result);

        System.exit(1);
    }

    /**
     * TODO
     *
     * @throws IOException TODO
     */
    public Controller(String path) throws IOException {
        initializeView();
        initializeFileStorageFactory(path);
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
        FileStorageFactoryResult result;

        if (path == null) {
            result = fileStorageFactory.open();
        } else {
            result = fileStorageFactory.open(path);
        }

        if (result == FileStorageFactoryResult.SUCCESS) {
            updateView();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FileStorageFactoryResult saveFile(String path) {
        FileStorageFactoryResult result = fileStorageFactory.save(path, lastSavePath.equalsIgnoreCase(path));

        if (result == FileStorageFactoryResult.SUCCESS) {
            this.lastSavePath = path;
        }

        return result;
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
