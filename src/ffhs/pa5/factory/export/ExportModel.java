package ffhs.pa5.factory.export;

import ffhs.pa5.model.*;
import ffhs.pa5.model.type.State;
import ffhs.pa5.util.Logger;

import java.util.ArrayList;
import java.util.Date;

/**
 * Export model contains the important fields
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ExportModel {

    private String title;
    private String location;
    private Date date;
    private ArrayList<ExportModelParticipant> participants;
    private State state;
    private Date nextMeeting;
    private ArrayList<ExportModelAgendaItem> agendaItems;
    private Date creationDate;
    private Date lastEditionDate;
    private Date exportDate;

    /**
     * The method get the filled out information and export that
     *
     * @param dataFile TODO
     */
    public ExportModel(DataFile dataFile, Date creationDate, Date lastEditionDate) {
        Meeting meeting = dataFile.getMeeting();

        this.title = meeting.getTitle();
        this.location = meeting.getLocation();
        this.date = meeting.getDate();
        this.participants = new ArrayList<>();
        for (Participant participant : meeting.getParticipants()) {
            this.participants.add(new ExportModelParticipant(participant));
        }
        this.state = meeting.getState();
        this.nextMeeting = meeting.getNextMeeting();
        this.agendaItems = new ArrayList<>();
        for (AgendaItem agendaItem : meeting.getAgendaItems()) {
            if (agendaItem.isDeleted()) {
                continue;
            }

            ExportModelAgendaItem exportModelAgendaItem;
            try {
                exportModelAgendaItem = new ExportModelAgendaItem(agendaItem);
            } catch (Exception ex) {
                Logger logger = Logger.getInstance();
                logger.handleException(ex);

                continue;
            }
            this.agendaItems.add(exportModelAgendaItem);
        }
        this.creationDate = creationDate;
        this.lastEditionDate = lastEditionDate;
        this.exportDate = new Date();
    }

    String getTitle() {
        return title;
    }

    String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    ArrayList<ExportModelParticipant> getParticipants() {
        return participants;
    }

    public State getState() {
        return state;
    }

    Date getNextMeeting() {
        return nextMeeting;
    }

    ArrayList<ExportModelAgendaItem> getAgendaItems() {
        return agendaItems;
    }

    Date getCreationDate() {
        return creationDate;
    }

    Date getLastEditionDate() {
        return lastEditionDate;
    }

    Date getExportDate() {
        return exportDate;
    }
}
