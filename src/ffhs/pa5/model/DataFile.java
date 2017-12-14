package ffhs.pa5.model;

import java.util.ArrayList;
import java.util.Observer;

/**
 * This class handles the data of a meeting protocol.
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
     *
     * @param password TODO
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
    public void addObserverRecursive(Observer observer) {
        metadata.addObserverRecursive(observer);
        meeting.addObserverRecursive(observer);
        for (Change change : changes) {
            change.addObserverRecursive(observer);
        }
        addObserver(observer);
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Meeting getMeeting() {
        return meeting;
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
