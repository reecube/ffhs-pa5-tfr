package ffhs.pa5.model;

import java.util.ArrayList;
import java.util.Date;

public class Metadata {
    private int appVersion;
    private String id;
    private Date saveDate;
    private String password;
    private ArrayList<String> attachments;

    public Metadata(int appVersion, String id, Date saveDate, String password, ArrayList<String> attachments) {
        this.appVersion = appVersion;
        this.id = id;
        this.saveDate = saveDate;
        this.password = password;
        this.attachments = attachments;
    }

    public int getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(int appVersion) {
        this.appVersion = appVersion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<String> attachments) {
        this.attachments = attachments;
    }
}
