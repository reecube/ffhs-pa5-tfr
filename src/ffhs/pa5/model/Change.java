package ffhs.pa5.model;

import java.util.Date;

/**
 * This class handles the data of changes made in a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Change extends ViewObservable {

    private Date date;
    private String user;
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
