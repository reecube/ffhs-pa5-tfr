package ffhs.pa5.view;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.controller.ViewController;
import ffhs.pa5.factory.export.ExportFactory;
import ffhs.pa5.factory.export.ExportOutputHandler;
import ffhs.pa5.factory.export.FileExportOutputHandler;
import ffhs.pa5.factory.storage.FileStorageFactoryResult;
import ffhs.pa5.model.*;
import ffhs.pa5.model.type.*;
import ffhs.pa5.util.DateUtil;
import ffhs.pa5.util.Logger;
import ffhs.pa5.util.ResourceUtil;
import ffhs.pa5.view.dialog.AgendaItemDialog;
import ffhs.pa5.view.dialog.ParticipantDialog;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * The View class. Shows the stage and the first scene.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class View extends Stage implements Observer, Initializable {

    private ViewController controller;


    // ********************************************************************************
    // fxml components
    // ********************************************************************************

    // Global

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabPreparation;

    @FXML
    private Tab tabMeeting;

    @FXML
    private Tab tabEnding;


    // Tab preparation

    @FXML
    private TextField inputMeetingTitle;

    @FXML
    private DatePicker inputMeetingDate;

    @FXML
    private TextArea inputMeetingLocation;

    @FXML
    private ListView<Participant> outputParticipants;

    @FXML
    private Button buttonParticipantEdit;

    @FXML
    private Button buttonParticipantRemove;

    @FXML
    private ListView<AgendaItem> outputAgendaItemsPreparation;

    @FXML
    private Button buttonAgendaItemEdit;

    @FXML
    private Button buttonAgendaItemRemove;

    @FXML
    private Button buttonAgendaItemMoveUp;

    @FXML
    private Button buttonAgendaItemMoveDown;


    // Tab meeting

    @FXML
    private ListView<AgendaItem> outputAgendaItemsMeeting;

    @FXML
    private TextArea inputAgendaItemContent;

    @FXML
    private Button buttonAgendaItemContentSave;


    // Tab ending

    @FXML
    private DatePicker inputMeetingNextMeeting;

    @FXML
    private ChoiceBox<ExportOutputHandler> inputExport;

    @FXML
    private Button buttonExport;


    // ********************************************************************************
    // main code
    // ********************************************************************************

    public void setController(ViewController controller) {
        this.controller = controller;
    }

    /**
     * Add change listener
     *
     * @param listView listView
     * @param listener listener
     * @param <T>      listener
     */
    private static <T> void addChangeListener(ListView<T> listView, ChangeListener<T> listener) {
        listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    /**
     * Initialize the export
     */
    private void initializeExport() {
        ExportOutputHandler[] exportOutputHandlers = ExportFactory.getAvailableHandlers();
        boolean disableExport = exportOutputHandlers.length == 0;
        inputExport.setDisable(disableExport);
        buttonExport.setDisable(disableExport);
        ObservableList<ExportOutputHandler> exportList = inputExport.getItems();
        exportList.clear();
        exportList.addAll(exportOutputHandlers);
        if (!disableExport) {
            inputExport.getSelectionModel().select(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)
                -> onTabPaneSelectionChange(newValue));

        inputMeetingTitle.textProperty().addListener((ov, oldValue, newValue)
                -> controller.changeMeetingTitle(newValue));
        inputMeetingDate.valueProperty().addListener((ov, oldValue, newValue)
                -> controller.changeMeetingDate(newValue));
        inputMeetingLocation.textProperty().addListener((ov, oldValue, newValue)
                -> controller.changeMeetingLocation(newValue));


        inputAgendaItemContent.textProperty().addListener((ov, oldValue, newValue)
                -> onChangeAgendaItemContent(newValue));

        addChangeListener(outputParticipants, (observable, oldValue, newValue)
                -> onParticipantsSelectionChange(newValue));

        addChangeListener(outputAgendaItemsPreparation, (observable, oldValue, newValue)
                -> onAgendaItemsPreparationSelectionChange(newValue));

        addChangeListener(outputAgendaItemsMeeting, (observable, oldValue, newValue)
                -> onAgendaItemsMeetingSelectionChange(newValue));


        inputMeetingNextMeeting.valueProperty().addListener((ov, oldValue, newValue)
                -> controller.changeMeetingNextMeeting(newValue));

        initializeExport();
    }

    /**
     * Update agenda items
     *
     * @param agendaItems agendaItems
     */
    private void updateAgendaItems(AgendaItem[] agendaItems) {
        SelectionModel<AgendaItem> selectionModelPreparation = outputAgendaItemsPreparation.getSelectionModel();
        AgendaItem selectedItemPreparation = selectionModelPreparation.getSelectedItem();
        ObservableList<AgendaItem> listPreparation = outputAgendaItemsPreparation.getItems();
        listPreparation.clear();
        listPreparation.addAll(agendaItems);
        if (listPreparation.contains(selectedItemPreparation)) {
            selectionModelPreparation.select(selectedItemPreparation);
        }

        SelectionModel<AgendaItem> selectionModelMeeting = outputAgendaItemsMeeting.getSelectionModel();
        AgendaItem selectedItemMeeting = selectionModelMeeting.getSelectedItem();
        ObservableList<AgendaItem> listMeeting = outputAgendaItemsMeeting.getItems();
        listMeeting.clear();
        listMeeting.addAll(agendaItems);
        if (listMeeting.contains(selectedItemMeeting)) {
            selectionModelMeeting.select(selectedItemMeeting);
        }
    }

    /**
     * Update the participants
     *
     * @param participants participants
     */
    private void updateParticipants(Participant[] participants) {
        SelectionModel<Participant> selectionModel = outputParticipants.getSelectionModel();
        Participant selectedItem = selectionModel.getSelectedItem();
        ObservableList<Participant> list = outputParticipants.getItems();
        list.clear();
        list.addAll(participants);
        if (list.contains(selectedItem)) {
            selectionModel.select(selectedItem);
        }
    }

    /**
     * Check if the meeting is ready to start
     *
     * @param meeting meeting
     * @return meeting
     */
    private boolean isMeetingReadyToStart(Meeting meeting) {
        return meeting.getAgendaItems().length != 0 && !meeting.getTitle().equals("");

    }

    /**
     * Check if the meeting is ready to end
     *
     * @param meeting the meeting
     * @return true on success
     */
    private boolean isMeetingReadyToEnd(Meeting meeting) {
        boolean readyToEnd = true;
        AgendaItem[] agendaItems = meeting.getAgendaItems();
        for (AgendaItem agendaItem : agendaItems) {
            if (agendaItem.getContent().length() == 0) {
                readyToEnd = false;
                break;
            }
        }
        return readyToEnd;
    }

    /**
     * Update the informations for meeting
     *
     * @param meeting meeting
     */
    private void updateMeeting(Meeting meeting) {
        inputMeetingTitle.setText(meeting.getTitle());
        inputMeetingDate.setValue(DateUtil.toLocalDate(meeting.getDate()));
        inputMeetingLocation.setText(meeting.getLocation());
        inputMeetingNextMeeting.setValue(DateUtil.toLocalDate(meeting.getNextMeeting()));

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        switch (meeting.getState()) {
            case PREPARATION:
                selectionModel.select(tabPreparation);
                tabPreparation.setDisable(false);
                tabMeeting.setDisable(!isMeetingReadyToStart(meeting));
                tabEnding.setDisable(true);
                break;
            case MEETING:
                selectionModel.select(tabMeeting);
                tabPreparation.setDisable(false);
                tabMeeting.setDisable(false);
                tabEnding.setDisable(!isMeetingReadyToEnd(meeting));
                break;
            case ENDING:
                selectionModel.select(tabEnding);
                tabPreparation.setDisable(true);
                tabMeeting.setDisable(false);
                tabEnding.setDisable(false);
                break;
            case CLOSED:
                selectionModel.select(tabEnding);
                tabPreparation.setDisable(true);
                tabMeeting.setDisable(true);
                tabEnding.setDisable(true);
                break;
            default:
                selectionModel.select(tabEnding);
                tabPreparation.setDisable(false);
                tabMeeting.setDisable(false);
                tabEnding.setDisable(false);
                break;
        }

        updateAgendaItems(meeting.getAgendaItems());
        updateParticipants(meeting.getParticipants());
    }

    /**
     * Update the data file
     *
     * @param dataFile dataFile
     */
    private void updateDataFile(DataFile dataFile) {
        updateMeeting(dataFile.getMeeting());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof ViewObservable) {
            ViewObservable observable = (ViewObservable) o;
            updateDataFile(observable.getFile());
        } else {
            final Logger logger = Logger.getInstance();
            logger.handleException(new Exception("Could not handle the observable with the class `" + o.getClass() + "`!"));
        }
    }

    /**
     * Definition the pane selection and changing
     *
     * @param newValue newValue
     */
    private void onTabPaneSelectionChange(Tab newValue) {
        State newState;

        if (newValue.equals(tabPreparation)) {
            if (controller.getState().equals(State.MEETING)) {
                AlertHelper.showWarning(LanguageKey.WARNING_CHANGE_TAB_REVERSE);
            }
            newState = State.PREPARATION;
        } else if (newValue.equals(tabMeeting)) {
            if (controller.getState().equals(State.ENDING)) {
                AlertHelper.showWarning(LanguageKey.WARNING_CHANGE_TAB_REVERSE);
            }
            newState = State.MEETING;
        } else if (newValue.equals(tabEnding)) {
            newState = State.ENDING;
        } else {
            Logger logger = Logger.getInstance();
            logger.handleException(new Exception("Unexpected behavior!"));
            return;
        }

        controller.changeState(newState);
    }

    /**
     * Change on select the participant
     *
     * @param newValue newValue
     */
    private void onParticipantsSelectionChange(Participant newValue) {
        boolean disabledBecauseOfSelection = newValue == null;
        buttonParticipantEdit.setDisable(disabledBecauseOfSelection);
        buttonParticipantRemove.setDisable(disabledBecauseOfSelection);
    }

    /**
     * Handle the agenda buttons by edit, remove, moveup and movedown
     *
     * @param newValue newValue
     */
    private void onAgendaItemsPreparationSelectionChange(AgendaItem newValue) {
        boolean disabledBecauseOfSelection = newValue == null;
        buttonAgendaItemEdit.setDisable(disabledBecauseOfSelection);
        buttonAgendaItemRemove.setDisable(disabledBecauseOfSelection);
        buttonAgendaItemMoveUp.setDisable(disabledBecauseOfSelection);
        buttonAgendaItemMoveDown.setDisable(disabledBecauseOfSelection);
    }

    /**
     * Input a new content by selection agenda item
     *
     * @param newValue newValue
     */
    private void onAgendaItemsMeetingSelectionChange(AgendaItem newValue) {
        boolean disabledBecauseOfSelection = newValue == null;
        inputAgendaItemContent.setText(disabledBecauseOfSelection ? "" : newValue.getContent());
        inputAgendaItemContent.setDisable(disabledBecauseOfSelection);
    }

    /**
     * The participant on add
     */
    public void onAddParticipant() {
        ParticipantDialog participantDialog = ParticipantDialog.getNewInstance();
        Optional<Participant> result = participantDialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }

        if (controller.addParticipant(result.get())) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_ADD, null);
    }

    /**
     * The participant on edit
     */
    public void onEditParticipant() {
        Participant participant = outputParticipants.getSelectionModel().getSelectedItem();

        if (participant == null) {
            AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_NULL, null);
            return;
        }

        ParticipantDialog participantDialog = ParticipantDialog.getNewInstance(participant);
        if (participantDialog == null) {
            // Exception already handled
            return;
        }
        Optional<Participant> result = participantDialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }

        if (controller.editParticipant(result.get())) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_EDIT, null);
    }

    /**
     * The participant on remove
     */
    public void onRemoveParticipant() {
        Participant participant = outputParticipants.getSelectionModel().getSelectedItem();

        if (participant == null) {
            AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_NULL, null);
            return;
        }

        if (controller.removeParticipant(participant)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_REMOVE, null);
    }

    /**
     * The agenda item on add
     */
    public void onAddAgendaItem() {
        AgendaItemDialog agendaItemDialog = AgendaItemDialog.getNewInstance();
        Optional<AgendaItem> result = agendaItemDialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }

        if (controller.addAgendaItem(result.get())) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_ADD, null);
    }

    /**
     * The agenda item on edit
     */
    public void onEditAgendaItem() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL, null);
            return;
        }

        AgendaItemDialog agendaItemDialog = AgendaItemDialog.getNewInstance(agendaItem);
        if (agendaItemDialog == null) {
            // Exception already handled
            return;
        }
        Optional<AgendaItem> result = agendaItemDialog.showAndWait();
        if (!result.isPresent()) {
            return;
        }

        if (controller.editAgendaItem(result.get())) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_EDIT, null);
    }

    /**
     * The agenda item on remove
     */
    public void onRemoveAgendaItem() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL, null);
            return;
        }

        if (controller.removeAgendaItem(agendaItem)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_REMOVE, null);
    }

    /**
     * The agenda item on move up
     */
    public void onMoveAgendaItemUp() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL, null);
            return;
        }

        if (controller.moveAgendaItem(agendaItem, true)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_MOVE, null);
    }

    /**
     * The agenda item on move down
     */
    public void onMoveAgendaItemDown() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL, null);
            return;
        }

        if (controller.moveAgendaItem(agendaItem, false)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_MOVE, null);
    }

    /**
     * The agenda item on change content
     *
     * @param newValue newValue
     */
    private void onChangeAgendaItemContent(String newValue) {
        AgendaItem agendaItem = outputAgendaItemsMeeting.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            return;
        }

        buttonAgendaItemContentSave.setDisable(newValue.equals(agendaItem.getContent()));
    }

    /**
     * The agenda item on save content
     */
    public void onSaveAgendaItemContent() {
        AgendaItem agendaItem = outputAgendaItemsMeeting.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL, null);
            return;
        }

        agendaItem.setContent(inputAgendaItemContent.getText());

        if (controller.editAgendaItem(agendaItem)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_EDIT, null);
    }

    /**
     * Handling on export meeting
     */
    public void onExportMeeting() {
        ExportOutputHandler handler = inputExport.getValue();

        if (handler == null) {
            AlertHelper.showError(LanguageKey.ERROR_HANDLER_NULL, null);
            return;
        }

        String path = null;

        if (handler instanceof FileExportOutputHandler) {
            FileExportOutputHandler fileExportOutputHandler = (FileExportOutputHandler) handler;

            String initialDirectory = getInitialDirectory();
            File file = FileChooserHelper.showExportDialog(initialDirectory,
                    fileExportOutputHandler.getFileExtension(), this);

            if (file == null) {
                // Happens on user cancel
                return;
            }

            path = file.getAbsolutePath();
        }

        boolean result = controller.export(handler, path);

        if (result) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_EXPORT, null);
    }

    /**
     * Close the meeting on close meeting
     */
    public void onCloseMeeting() {
        controller.closeMeeting();
    }

    /**
     * Handling the file storage factory result
     *
     * @param result result
     */
    private void handleFileStorageFactoryResult(FileStorageFactoryResult result) {
        if (result == FileStorageFactoryResult.SUCCESS) {
            return;
        }

        String errorMessage = "";
        switch (result) {
            case ERROR_UNKNOWN:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_UNKNOWN));
                break;
            case ERROR_UNINITIALIZED:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_UNINITIALIZED));
                break;
            case ERROR_UNEXPECTED_BEHAVIOR:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_UNEXPECTED_BEHAVIOR));
                break;
            case ERROR_FILE_NOT_FOUND:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_FILE_NOT_FOUND));
                break;
            case ERROR_FILE_INVALID:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_FILE_INVALID));
                break;
            case ERROR_FILE_LOCKED:
                // TODO: show username
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_FILE_LOCKED));
                break;
            case ERROR_FILE_VERSION_MISMATCH:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_FILE_VERSION_MISMATCH));
                break;
            case ERROR_LOCKSTATE_FAILED:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_LOCKSTATE_FAILED));
                break;
            case ERROR_BUILD_ARCHIVE:
                errorMessage = (ResourceUtil.getLangString(Controller.getBundle(), LanguageKey.ERROR_BUILD_ARCHIVE));
                break;
            default:
                break;
        }

        AlertHelper.showError(LanguageKey.ERROR_STORAGE, errorMessage);
    }

    /**
     * Get the initial directory
     *
     * @return lastSavedFile
     */
    private String getInitialDirectory() {
        String lastSavedPath = controller.getLastSavePath();

        if (lastSavedPath == null) {
            return System.getProperty(Constants.JAVA_USER_HOME);
        }

        File lastSavedFile = new File(lastSavedPath);

        return lastSavedFile.getAbsoluteFile().getParent();
    }

    /**
     * Get the initial filename
     *
     * @return FileChooserHelper
     */
    private String getInitialFilename(String extension) {
        String lastSavedPath = controller.getLastSavePath();

        if (lastSavedPath == null) {
            return FileChooserHelper.getFileNameFromMeetingTitle(inputMeetingTitle.getText(), extension);
        }

        File lastSavedFile = new File(lastSavedPath);

        return lastSavedFile.getParent();
    }

    private void handleMenuOpen(String path) {
        handleFileStorageFactoryResult(controller.openFile(path));
    }

    /**
     * Handling on new menu
     */
    public void onMenuNew() {
        handleMenuOpen(null);
    }

    /**
     * Handling on open menu
     */
    public void onMenuOpen() {
        String initialDirectory = getInitialDirectory();
        System.out.println(initialDirectory);
        File file = FileChooserHelper.showOpenDialog(initialDirectory, this);

        if (file == null || !file.exists() || !file.isFile()) {
            return;
        }

        handleMenuOpen(file.getAbsolutePath());
    }

    /**
     * Handling on save menu
     */
    private void handleMenuSave() {
        String initialDirectory = getInitialDirectory();
        String initialFileName = getInitialFilename(Constants.APP_FILEEXTENSION_SPV);
        File file = FileChooserHelper.showSaveDialog(initialDirectory, initialFileName, this);

        if (file == null) {
            return;
        }

        handleFileStorageFactoryResult(controller.saveFile(file.getAbsolutePath()));
    }

    /**
     * Handling on save menu by getting the last save path
     */
    public void onMenuSave() {
        String lastSavedPath = controller.getLastSavePath();

        if (lastSavedPath == null) {
            onMenuSaveAs();
            return;
        }

        handleFileStorageFactoryResult(controller.saveFile(lastSavedPath));
    }

    /**
     * Handling on save as menu
     */
    public void onMenuSaveAs() {
        handleMenuSave();
    }
}
