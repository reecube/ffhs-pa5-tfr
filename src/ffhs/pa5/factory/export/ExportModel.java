package ffhs.pa5.factory.export;

import ffhs.pa5.model.AgendaItem;
import ffhs.pa5.model.DataFile;
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

    public ExportModel(DataFile dataFile) {
        // TODO: Barbara, fülle die Variablen anhand der Parameter
        //TODO > auch infos zum Benutzer, der bearbeitet hat, exportieren? Nein
        //TODO > Wie muss ich hier mit einer NullPointerException umgehen? Sollte keine auftreten! ;)
    }
}
