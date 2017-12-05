package ffhs.pa5.model;

/**
 * This class handles the data of agenda items within a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class AgendaItem extends ViewObservable {

    private String id;
    private String title;
    private String content;
    private Boolean deleted;

    public AgendaItem(String id, String title, String content, Boolean deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deleted = deleted;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;

        updateView();
    }
}
