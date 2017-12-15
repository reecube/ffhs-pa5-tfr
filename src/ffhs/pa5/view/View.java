package ffhs.pa5.view;

import ffhs.pa5.controller.ViewController;
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

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab tabPreparation;

    @FXML
    private Tab tabMeeting;

    @FXML
    private Tab tabEnding;

    @FXML
    private TextField inputMeetingTitle;

    @FXML
    private DatePicker inputMeetingDate;

    @FXML
    private TextArea inputMeetingLocation;

    @FXML
    private ListView<Participant> outputParticipants;

    @FXML
    private ListView<AgendaItem> outputAgendaItemsPreparation;

    @FXML
    private ListView<AgendaItem> outputAgendaItemsMeeting;

    @FXML
    private TextArea inputAgendaItemContent;

    @FXML
    private DatePicker inputMeetingNextMeeting;

    @FXML
    private ChoiceBox inputExport;

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
                -> onTabPaneSelectionChange(oldValue, newValue));

        addChangeListener(outputParticipants, (observable, oldValue, newValue)
                -> onParticipantsSelectionChange(oldValue, newValue));

        addChangeListener(outputAgendaItemsPreparation, (observable, oldValue, newValue)
                -> onAgendaItemsPreparationSelectionChange(oldValue, newValue));

        addChangeListener(outputAgendaItemsMeeting, (observable, oldValue, newValue)
                -> onAgendaItemsMeetingSelectionChange(oldValue, newValue));
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
        ObservableList<AgendaItem> listMeeting = outputAgendaItemsMeeting.getItems();
        listMeeting.clear();
        listMeeting.addAll(agendaItems);

        ObservableList<AgendaItem> listPreparation = outputAgendaItemsPreparation.getItems();
        listPreparation.clear();
        listPreparation.addAll(agendaItems);
    }

    /**
     * TODO
     *
     * @param participants TODO
     */
    private void updateParticipants(Participant[] participants) {
        ObservableList<Participant> list = outputParticipants.getItems();
        list.clear();
        list.addAll(participants);
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
                tabMeeting.setDisable(false);
                tabEnding.setDisable(false);
                break;
            case MEETING:
                selectionModel.select(tabMeeting);
                tabPreparation.setDisable(false);
                tabMeeting.setDisable(false);
                tabEnding.setDisable(false);
                break;
            case ENDING:
                selectionModel.select(tabEnding);
                tabPreparation.setDisable(false);
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
     * @param oldValue TODO
     * @param newValue TODO
     */
    private void onTabPaneSelectionChange(Tab oldValue, Tab newValue) {
        System.out.println(oldValue);
        System.out.println(newValue);
    }

    /**
     * TODO
     *
     * @param oldValue TODO
     * @param newValue TODO
     */
    private void onParticipantsSelectionChange(Participant oldValue, Participant newValue) {
        // TODO: implement this
        System.out.println(oldValue);
        System.out.println(newValue);
    }

    /**
     * TODO
     *
     * @param oldValue TODO
     * @param newValue TODO
     */
    private void onAgendaItemsPreparationSelectionChange(AgendaItem oldValue, AgendaItem newValue) {
        // TODO: implement this
        System.out.println(oldValue);
        System.out.println(newValue);
    }

    /**
     * TODO
     *
     * @param oldValue TODO
     * @param newValue TODO
     */
    private void onAgendaItemsMeetingSelectionChange(AgendaItem oldValue, AgendaItem newValue) {
        // TODO: implement this
        System.out.println(oldValue);
        System.out.println(newValue);
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
}
