package ffhs.pa5.factory.export;

import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.Participant;
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

//TODO > Gehört das ins package export oder model?

public class ExportModel {

    private String title;
    private String location;
    private Date date;
    private ArrayList<Participant> participants;
    private State state;
    private Date nextMeeting;
    private ArrayList<AgendaItem> agendaItems;
    private Date creationDate;
    private Date lastEditionDate;
    private Date exportDate;

    public ExportModel (String title, String location, Date date, ArrayList<Participant> participants,
                        State state, Date nextMeeting, ArrayList<AgendaItem> agendaItems, Date creationDate,
                        Date lastEditionDate, Date exportDate){
        this.title = title;
        this.location = location;
        this.date = date;
        this.participants = participants;
        this.state = state;
        this.nextMeeting = nextMeeting;
        this.agendaItems = agendaItems;
        this.creationDate = creationDate;
        this.lastEditionDate = lastEditionDate;
        this.exportDate = exportDate;

        //TODO > auch infos zum Benutzer, der bearbeitet hat, exportieren?
        //TODO > Wie muss ich hier mit einer NullPointerException umgehen?
    }

    // TODO > Ist das so genügend Read-only? ;)
}
