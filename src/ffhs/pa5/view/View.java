package ffhs.pa5.view;

import ffhs.pa5.model.*;
import ffhs.pa5.util.DateUtil;
import ffhs.pa5.util.Logger;
import ffhs.pa5.view.dialog.AgendaItemDialog;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 * The View class. Shows the stage and the first scene.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class View extends Stage implements Observer {

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
    private void updateAgendaItems(ArrayList<AgendaItem> agendaItems) {
        ObservableList<AgendaItem> list = outputAgendaItemsMeeting.getItems();
        list.addAll(agendaItems);
    }

    /**
     * TODO
     *
     * @param participants TODO
     */
    private void updateParticipants(ArrayList<Participant> participants) {
        ObservableList<Participant> list = outputParticipants.getItems();
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
                tabEnding.setDisable(true);
                break;
            case MEETING:
                selectionModel.select(tabMeeting);
                tabPreparation.setDisable(true);
                tabMeeting.setDisable(false);
                tabEnding.setDisable(false);
                break;
            case ENDING:
                selectionModel.select(tabEnding);
                tabPreparation.setDisable(true);
                tabMeeting.setDisable(true);
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
    private void updateChanges(ArrayList<Change> changes) {
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
     * @param actionEvent TODO
     */
    public void onAddAgendaItem(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onEditAgendaItem(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onRemoveAgendaItem(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onAddParticipant(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onEditParticipant(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onRemoveParticipant(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onMoveAgendaItemUp(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onMoveAgendaItemDown(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onExportMeeting(ActionEvent actionEvent) {
    }

    /**
     * TODO
     *
     * @param actionEvent TODO
     */
    public void onCloseMeeting(ActionEvent actionEvent) {
    }
}
