package ffhs.pa5.factory.export;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.stage.FileChooser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class TextExportOutputHandler extends FileExportOutputHandler {

    private String lineSeparator;
    private String content;

    /**
     * {@inheritDoc}
     */
    @Override
    public FileChooser.ExtensionFilter[] getFileExtension() {
        return new FileChooser.ExtensionFilter[]{
                new FileChooser.ExtensionFilter("Text file (*.txt)", "*.txt")
        };
    }

    private void addLine(String line) {
        this.content = content + (line == null ? "" : line) + lineSeparator;
    }

    private void addLine() {
        addLine(null);
    }

    private void addLabelContent(LanguageKey label, String theContent) {
        String labelContent = ResourceUtil.getLangString(Controller.getBundle(), label);
        if (theContent != null && theContent.length() > 0) {
            addLine(labelContent + Constants.CHAR_TAB + theContent);
        } else {
            addLine(labelContent);
        }
    }

    private String formatDate(Date date) {
        if (date != null && date.toString().length() > 0) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return dateFormat.format(date);
        }
        return "";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] getContent(ExportModel model) {
        this.lineSeparator = System.getProperty(Constants.JAVA_LINE_SEPARATOR);
        this.content = "";

        addLabelContent(LanguageKey.EXPORT_TITLE, null);
        addLine();
        addLine(model.getTitle());
        addLabelContent(LanguageKey.EXPORT_MEETING_DATE, formatDate(model.getDate()));
        addLine(model.getLocation());
        addLine();
        addLabelContent(LanguageKey.EXPORT_PARTICIPANTS_LIST, null);
        for (ExportModelParticipant participant : model.getParticipants()) {
            addLine(participant.toString());
        }
        addLine();
        addLabelContent(LanguageKey.EXPORT_AGENDA_ITEMS, null);
        for (ExportModelAgendaItem agendaItem : model.getAgendaItems()) {
            addLine(agendaItem.toString());
        }
        addLine();
        addLabelContent(LanguageKey.EXPORT_NEXT_MEETING, formatDate(model.getNextMeeting()));

        return content.getBytes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TextExportOutputHandler";
    }
}
