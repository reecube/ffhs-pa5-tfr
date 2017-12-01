package ffhs.pa5.model;

import java.util.Observable;

/**
 * TODO
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
    void updateView() {
        setChanged();
        notifyObservers();
    }
}
