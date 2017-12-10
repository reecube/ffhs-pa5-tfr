package ffhs.pa5.view;

import ffhs.pa5.model.*;
import ffhs.pa5.util.Logger;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The View class. Shows the stage and the first scene.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class View extends Stage implements Observer {

    private void updateMetadata(Metadata metadata) {
        // TODO: implement this
    }

    private void updateAgendaItems(ArrayList<AgendaItem> agendaItems) {
        // TODO: implement this
    }

    private void updateParticipants(ArrayList<Participant> participants) {
        // TODO: implement this
    }

    private void updateMeeting(Meeting meeting) {
        updateAgendaItems(meeting.getAgendaItems());
        updateParticipants(meeting.getParticipants());
    }

    private void updateChanges(ArrayList<Change> changes) {
        // TODO: this will be implemented in a future version
    }

    private void updateDataFile(DataFile dataFile) {
        updateMetadata(dataFile.getMetadata());
        updateMeeting(dataFile.getMeeting());
        updateChanges(dataFile.getChanges());
    }

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
}
