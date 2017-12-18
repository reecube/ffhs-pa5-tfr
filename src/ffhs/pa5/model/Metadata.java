package ffhs.pa5.model;

import com.google.gson.annotations.Expose;
import ffhs.pa5.Constants;
import ffhs.pa5.view.ViewObservable;
import ffhs.pa5.view.ViewObservableReference;

import java.util.Date;
import java.util.UUID;

/**
 * This class handles the metadata of a meetingprotocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Metadata extends ViewObservableReference {

    @Expose
    private int appVersion = Constants.APP_VERSION;

    @Expose
    private String id = generateId();

    @Expose
    private Date saveDate = null;

    @Expose
    private String password;

    /**
     * Commit a password for meta data
     *
     * @param password password
     */
    Metadata(String password) {
        this.password = password;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObservableRecursive(ViewObservable observable) {
        setObservable(observable);
    }

    /**
     * Generate a new ID
     *
     * @return UUID
     */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public int getAppVersion() {
        return appVersion;
    }

    public String getId() {
        return id;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;

        updateView();
    }

    public String getPassword() {
        return password;
    }
}
