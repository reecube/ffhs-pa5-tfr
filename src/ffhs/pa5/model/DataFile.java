package ffhs.pa5.model;

import com.google.gson.annotations.Expose;
import ffhs.pa5.view.ViewObservable;
import ffhs.pa5.view.ViewObservableReference;

import java.util.ArrayList;

/**
 * This class handles the data of a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class DataFile extends ViewObservableReference {

    @Expose
    private Metadata metadata;

    @Expose
    private Meeting meeting;

    @Expose
    private ArrayList<Change> changes;

    /**
     * Commit a password for datafile
     *
     * @param password password
     */
    public DataFile(String password) {
        this.metadata = new Metadata(password);
        this.meeting = new Meeting();
        this.changes = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableRecursive(ViewObservable observable) {
        metadata.setObservableRecursive(observable);
        meeting.setObservableRecursive(observable);
        setObservable(observable);
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    /**
     * Get the changed informations
     *
     * @return changes
     */
    @SuppressWarnings("unused")
    public Change[] getChanges() {
        return changes.toArray(new Change[0]);
    }

    /**
     * Add a new change
     *
     * @param change change
     * @return result
     */
    @SuppressWarnings("unused")
    public boolean addChange(Change change) {
        boolean result = changes.add(change);

        if (result) {
            updateView();
        }

        return result;
    }
}
