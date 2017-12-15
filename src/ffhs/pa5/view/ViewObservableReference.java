package ffhs.pa5.view;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class ViewObservableReference {

    private ViewObservable observable;

    protected void setObservable(ViewObservable observable) {
        this.observable = observable;
    }

    /**
     * Notify the observers for changes
     */
    public void updateView() {
        observable.updateView();
    }

    public abstract void setObservableRecursive(ViewObservable observable);
}
