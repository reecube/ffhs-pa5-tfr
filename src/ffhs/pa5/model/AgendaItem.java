package ffhs.pa5.model;

public class AgendaItem {
    private String id;
    private String title;
    private String data;
    private Boolean deleted;

    public AgendaItem(String id, String title, String data, Boolean deleted) {
        this.id = id;
        this.title = title;
        this.data = data;
        this.deleted = deleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
