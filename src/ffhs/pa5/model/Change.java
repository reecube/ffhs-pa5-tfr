package ffhs.pa5.model;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.Observer;

/**
 * This class handles the data of changes made in a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Change extends ViewObservable {

    @Expose
    private Date date;

    @Expose
    private String user;

    @Expose
    private String change;

    /**
     * TODO
     *
     * @param date TODO
     * @param user TODO
     * @param change TODO
     */
    public Change(Date date, String user, String change) {
        this.date = date;
        this.user = user;
        this.change = change;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserverRecursive(Observer observer) {
        addObserver(observer);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;

        updateView();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;

        updateView();
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;

        updateView();
    }
}
