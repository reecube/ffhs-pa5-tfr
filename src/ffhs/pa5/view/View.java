package ffhs.pa5.view;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.ViewController;
import ffhs.pa5.factory.storage.FileStorageFactoryResult;
import ffhs.pa5.model.*;
import ffhs.pa5.util.DateUtil;
import ffhs.pa5.util.Logger;
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


    // Tab ending

    @FXML
    private DatePicker inputMeetingNextMeeting;

    @FXML
    private ChoiceBox inputExport;


    // ********************************************************************************
    // main code
    // ********************************************************************************

    public void setController(ViewController controller) {
        this.controller = controller;
    }

    private static <T> void addChangeListener(ListView<T> listView, ChangeListener<T> listener) {
        listView.getSelectionModel().selectedItemProperty().addListener(listener);
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
    }

    /**
     * TODO
     *
     * @param metadata TODO
     */
    private void updateMetadata(Metadata metadata) {
        // TODO: implement this
    }

    /**
     * TODO
     *
     * @param agendaItems TODO
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
     * TODO
     *
     * @param participants TODO
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
     * TODO
     *
     * @param meeting TODO
     * @return TODO
     */
    private boolean isMeetingReadyToStart(Meeting meeting) {
        return meeting.getAgendaItems().length != 0 && !meeting.getTitle().equals("");

    }

    /**
     * TODO
     *
     * @param meeting TODO
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
                tabEnding.setDisable(false);
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
     * TODO
     *
     * @param changes TODO
     */
    private void updateChanges(Change[] changes) {
        // TODO: this will be implemented in a future version
    }

    /**
     * TODO
     *
     * @param dataFile TODO
     */
    private void updateDataFile(DataFile dataFile) {
        updateMetadata(dataFile.getMetadata());
        updateMeeting(dataFile.getMeeting());
        updateChanges(dataFile.getChanges());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof DataFile) {
            updateDataFile((DataFile) o);
        } else if (o instanceof Metadata) {
            updateMetadata((Metadata) o);
        } else if (o instanceof Meeting) {
            updateMeeting((Meeting) o);
        } else {
            final Logger logger = Logger.getInstance();
            logger.handleException(new Exception("Could not handle the observable with the class `" + o.getClass() + "`!"));
        }
    }

    /**
     * TODO
     *
     * @param newValue TODO
     */
    private void onTabPaneSelectionChange(Tab newValue) {
        State newState;

        if (newValue.equals(tabPreparation)) {
            newState = State.PREPARATION;
        } else if (newValue.equals(tabMeeting)) {
            newState = State.MEETING;
        } else if (newValue.equals(tabEnding)) {
            newState = State.ENDING;
        } else {
            Logger logger = Logger.getInstance();
            logger.handleException(new Exception("Unexpected behavior!"));
            return;
        }

        boolean result = controller.changeState(newState);

        if (result) {
            return;
        }

        Logger logger = Logger.getInstance();
        logger.handleException(new Exception("Unexpected behavior!"));
    }

    /**
     * TODO
     *
     * @param newValue TODO
     */
    private void onParticipantsSelectionChange(Participant newValue) {
        boolean disabledBecauseOfSelection = newValue == null;
        buttonParticipantEdit.setDisable(disabledBecauseOfSelection);
        buttonParticipantRemove.setDisable(disabledBecauseOfSelection);
    }

    /**
     * TODO
     *
     * @param newValue TODO
     */
    private void onAgendaItemsPreparationSelectionChange(AgendaItem newValue) {
        boolean disabledBecauseOfSelection = newValue == null;
        buttonAgendaItemEdit.setDisable(disabledBecauseOfSelection);
        buttonAgendaItemRemove.setDisable(disabledBecauseOfSelection);
        buttonAgendaItemMoveUp.setDisable(disabledBecauseOfSelection);
        buttonAgendaItemMoveDown.setDisable(disabledBecauseOfSelection);
    }

    /**
     * TODO
     *
     * @param newValue TODO
     */
    private void onAgendaItemsMeetingSelectionChange(AgendaItem newValue) {
        boolean disabledBecauseOfSelection = newValue == null;
        inputAgendaItemContent.setText(disabledBecauseOfSelection ? "" : newValue.getContent());
        inputAgendaItemContent.setDisable(disabledBecauseOfSelection);
    }

    /**
     * TODO
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

        AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_ADD);
    }

    /**
     * TODO
     */
    public void onEditParticipant() {
        Participant participant = outputParticipants.getSelectionModel().getSelectedItem();

        if (participant == null) {
            AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_NULL);
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

        AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_EDIT);
    }

    /**
     * TODO
     */
    public void onRemoveParticipant() {
        Participant participant = outputParticipants.getSelectionModel().getSelectedItem();

        if (participant == null) {
            AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_NULL);
            return;
        }

        if (controller.removeParticipant(participant)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_PARTICIPANT_REMOVE);
    }

    /**
     * TODO
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

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_ADD);
    }

    /**
     * TODO
     */
    public void onEditAgendaItem() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL);
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

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_EDIT);
    }

    /**
     * TODO
     */
    public void onRemoveAgendaItem() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL);
            return;
        }

        if (controller.removeAgendaItem(agendaItem)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_REMOVE);
    }

    /**
     * TODO
     */
    public void onMoveAgendaItemUp() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL);
            return;
        }

        if (controller.moveAgendaItem(agendaItem, true)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_MOVE);
    }

    /**
     * TODO
     */
    public void onMoveAgendaItemDown() {
        AgendaItem agendaItem = outputAgendaItemsPreparation.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL);
            return;
        }

        if (controller.moveAgendaItem(agendaItem, false)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_MOVE);
    }

    /**
     * TODO
     *
     * @param newValue TODO
     */
    private void onChangeAgendaItemContent(String newValue) {
        AgendaItem agendaItem = outputAgendaItemsMeeting.getSelectionModel().getSelectedItem();

        if (agendaItem == null) {
            AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_NULL);
            return;
        }

        agendaItem.setContent(newValue);

        if (controller.editAgendaItem(agendaItem)) {
            return;
        }

        AlertHelper.showError(LanguageKey.ERROR_AGENDAITEM_EDIT);
    }

    /**
     * TODO
     */
    public void onExportMeeting() {
        // TODO: implement this
    }

    /**
     * TODO
     */
    public void onCloseMeeting() {
        controller.closeMeeting();
    }

    /**
     * TODO
     *
     * @param result TODO
     */
    private void handleFileStorageFactoryResult(FileStorageFactoryResult result) {
        if (result == FileStorageFactoryResult.SUCCESS) {
            return;
        }

        // TODO: add error
        System.err.println(result);
        AlertHelper.showError(LanguageKey.ERROR_TITLE);
    }

    /**
     * TODO
     */
    public void onMenuOpen() {
        File file = FileChooserHelper.showOpenDialog(this);

        if (file == null || !file.exists() || !file.isFile()) {
            return;
        }

        handleFileStorageFactoryResult(controller.openFile(file.getAbsolutePath()));
    }

    /**
     * TODO
     *
     * @param extension TODO
     */
    private void handleMenuSave(String extension) {
        String initialFileName = FileChooserHelper.getFileNameFromMeetingTitle(
                inputMeetingTitle.getText(), extension);
        File file = FileChooserHelper.showSaveDialog(initialFileName, this);

        if (file == null) {
            return;
        }

        handleFileStorageFactoryResult(controller.saveFile(file.getAbsolutePath()));
    }

    /**
     * TODO
     */
    public void onMenuSave() {
        handleMenuSave(Constants.APP_FILEEXTENSION_SPV);
    }

    /**
     * TODO
     */
    public void onMenuSaveTemplate() {
        handleMenuSave(Constants.APP_FILEEXTENSION_SPV_TEMPLATE);
    }
}
