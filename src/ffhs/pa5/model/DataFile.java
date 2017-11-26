package ffhs.pa5.model;

import java.util.ArrayList;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class DataFile {

    private Metadata metadata;
    private Meeting meeting;
    private ArrayList<Change> changes;

    public DataFile(Metadata metadata, Meeting meeting, ArrayList<Change> changes) {
        this.metadata = metadata;
        this.meeting = meeting;
        this.changes = changes;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }

    public void setChanges(ArrayList<Change> changes) {
        this.changes = changes;
    }
}
