package ffhs.pa5.model;

import ffhs.pa5.Constants;

import java.util.ArrayList;
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
public class Metadata extends ViewObservable {

    private int appVersion = Constants.APP_VERSION;
    private String id = generateId();
    private Date saveDate = null;
    private String password;

    /**
     * TODO
     *
     * @param password TODO
     */
    Metadata(String password) {
        this.password = password;
    }

    /**
     * TODO
     *
     * @return TODO
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
