package ffhs.pa5.model;

import java.util.Observable;

/**
 * This is the Observable class with custom functions.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
class ViewObservable extends Observable {

    /**
     * Notify the observers for changes
     */
    public void updateView() {
        setChanged();
        notifyObservers();
    }
}
