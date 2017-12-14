package ffhs.pa5.factory.export;

import ffhs.pa5.model.*;
import ffhs.pa5.util.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * TODO
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
     * TODO
     *
     * @param dataFile TODO
     */
    public ExportModel(DataFile dataFile) {
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
        // this.creationDate TODO
        // this.lastEditionDate TODO
        this.exportDate = Calendar.getInstance().getTime();
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<ExportModelParticipant> getParticipants() {
        return participants;
    }

    public State getState() {
        return state;
    }

    public Date getNextMeeting() {
        return nextMeeting;
    }

    public ArrayList<ExportModelAgendaItem> getAgendaItems() {
        return agendaItems;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastEditionDate() {
        return lastEditionDate;
    }

    public Date getExportDate() {
        return exportDate;
    }
}
