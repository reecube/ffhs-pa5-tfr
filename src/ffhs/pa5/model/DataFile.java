package ffhs.pa5.model;

import java.util.ArrayList;

/**
 * TODO (Stimmt das so, Yves?)
 * This class handles the data of a meeting protocol which is saved on the file system.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class DataFile extends ViewObservable {

    private Metadata metadata;
    private Meeting meeting;
    private ArrayList<Change> changes;

    /**
     * TODO
     */
    public DataFile() {
        this.metadata = new Metadata("");
        this.meeting = new Meeting();
        this.changes = new ArrayList<>();
    }

    /**
     * TODO
     *
     * @param password TODO
     */
    public DataFile(String password) {
        this.metadata = new Metadata(password);
        this.meeting = new Meeting();
        this.changes = new ArrayList<>();
    }

    /**
     * TODO
     *
     * @param metadata TODO
     * @param meeting  TODO
     * @param changes  TODO
     */
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

        updateView();
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;

        updateView();
    }

    /**
     * TODO
     *
     * @return TODO
     */
    public Change[] getChanges() {
        return changes.toArray(new Change[0]);
    }

    /**
     * TODO
     *
     * @param change TODO
     * @return TODO
     */
    public boolean addChange(Change change) {
        boolean result = changes.add(change);

        if (result) {
            updateView();
        }

        return result;
    }
}
