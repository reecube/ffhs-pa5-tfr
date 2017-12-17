package ffhs.pa5.factory.export;

import ffhs.pa5.model.AgendaItem;

/**
 * Exporting the agenda item informations
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ExportModelAgendaItem {

    private String id;
    private String title;
    private String content;

    /**
     * This method export the agenda item if it's not deleted
     *
     * @param agendaItem TODO
     */
    public ExportModelAgendaItem(AgendaItem agendaItem) throws Exception {
        if (agendaItem.isDeleted()) {
            throw new Exception("You must not export a deleted agenda item!");
        }

        this.id = agendaItem.getId();
        this.title = agendaItem.getTitle();
        this.content = agendaItem.getContent();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
