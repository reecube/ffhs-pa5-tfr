package ffhs.pa5.factory.export;

import ffhs.pa5.model.AgendaItem;

/**
 * TODO
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
     * TODO
     *
     * @param agendaItem TODO
     */
    public ExportModelAgendaItem(AgendaItem agendaItem) {
        if (agendaItem.getDeleted() == false){
            this.id = agendaItem.getId();
            this.title = agendaItem.getTitle();
            this.content = agendaItem.getContent();
        }
        else {
            //TODO Muss ich hier noch was machen, damit keine leeren Instanzen entstehen können?
        }
        // TODO: Barbara, fülle die Variablen anhand der Parameter (ps. nur jene, welche nicht gelöscht sind)
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
