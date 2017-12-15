package ffhs.pa5.view;

import java.util.Observable;
import java.util.Observer;

/**
 * This is the Observable class with custom functions.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class ViewObservable extends Observable {

    public abstract void addObserverRecursive(Observer observer);

    /**
     * Notify the observers for changes
     */
    public void updateView() {
        setChanged();
        notifyObservers();
    }
}
