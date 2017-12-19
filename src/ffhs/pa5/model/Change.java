package ffhs.pa5.model;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * This class handles the data of changes made in a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Change {

    @Expose
    private Date date;

    @Expose
    private String user;

    @Expose
    private String change;

    /**
     * Handling with the date
     *
     * @param date   date
     * @param user   user
     * @param change change
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
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @SuppressWarnings("unused")
    public String getChange() {
        return change;
    }

    @SuppressWarnings("unused")
    public void setChange(String change) {
        this.change = change;
    }
}
