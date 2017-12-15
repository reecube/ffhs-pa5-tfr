package ffhs.pa5.model;

import com.google.gson.annotations.Expose;

import java.util.Observer;

/**
 * This class handles the data of agenda items within a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class AgendaItem extends ViewObservable {

    @Expose
    private String id = "";

    @Expose
    private String title = "";

    @Expose
    private String content = "";

    @Expose
    private boolean deleted = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserverRecursive(Observer observer) {
        addObserver(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + id + "] " + title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

        updateView();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;

        updateView();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;

        updateView();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;

        updateView();
    }
}
