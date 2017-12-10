package ffhs.pa5.factory.export;

import ffhs.pa5.model.DataFile;
import ffhs.pa5.model.State;

import java.util.ArrayList;
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
        // TODO: Barbara, fülle die Variablen anhand der Parameter
        //TODO > auch infos zum Benutzer, der bearbeitet hat, exportieren? Nein
        //TODO > Wie muss ich hier mit einer NullPointerException umgehen? Sollte keine auftreten! ;)
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
