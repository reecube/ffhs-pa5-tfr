package ffhs.pa5.view;

import ffhs.pa5.model.DataFile;

import java.util.Observable;

/**
 * This is the Observable class with custom functions.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ViewObservable extends Observable {

    private DataFile file;

    public ViewObservable(DataFile file) {
        this.file = file;

        file.setObservableRecursive(this);
    }

    public DataFile getFile() {
        return file;
    }

    /**
     * Notify the observers for changes
     */
    public void updateView() {
        setChanged();
        notifyObservers();
    }
}
