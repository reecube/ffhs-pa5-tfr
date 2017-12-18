package ffhs.pa5.factory.export;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.stage.FileChooser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Defines the text style
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
enum TextStyle {
    HEADER,
    TITLE,
    SUBTITLE,
    ITEM,
    TEXT,
    TEXT_LABELED,
    TEXT_MULTILINE,
}

/**
 * This class defines the text export output handler
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

    /**
     * This method adds a line
     *
     * @param line line
     */
    private void addLine(String line) {
        this.content = content + (line == null ? "" : line) + lineSeparator;
    }

    /**
     * This method parse the multiline content
     *
     * @param content content
     * @param level   level
     * @return result
     */
    private String parseMultilineContent(String content, int level) {
        StringBuilder result = new StringBuilder();

        char[] levelSpace = new char[level];
        for (int i = 0; i < levelSpace.length; i++) {
            levelSpace[i] = ' ';
        }

        content = content.replaceAll("\r", "");
        for (String line : content.split("\n")) {
            result.append(levelSpace).append("> ").append(line).append(lineSeparator);
        }

        return result.toString();
    }

    /**
     * This method adds a line
     *
     * @param style   style
     * @param content content
     */
    private void addLine(TextStyle style, String... content) {
        if (style == null || content.length == 0) {
            addLine(null);
            return;
        }

        String output;
        switch (style) {
            case HEADER:
                if (content.length % 2 != 0) {
                    return;
                }

                String[] headerEntries = new String[content.length / 2];
                for (int i = 0; i < headerEntries.length; i++) {
                    headerEntries[i] = content[i * 2].trim() + " " + content[i * 2 + 1].trim();
                }
                output = String.join(", ", headerEntries);

                char[] headerUnderline = new char[output.length()];
                for (int i = 0; i < headerUnderline.length; i++) {
                    headerUnderline[i] = '-';
                }
                output += lineSeparator + new String(headerUnderline) + lineSeparator + lineSeparator + lineSeparator;

                break;
            case TITLE:
                output = content[0].trim() + lineSeparator;
                output += "------------------------------" + lineSeparator;
                break;
            case SUBTITLE:
                output = content[0].trim() + ": " + lineSeparator;
                break;
            case ITEM:
                StringBuilder outputBuilder = new StringBuilder("- " + content[0].trim() + lineSeparator);
                for (int i = 1; i < content.length; i++) {
                    outputBuilder.append(parseMultilineContent(content[i], 2)).append(lineSeparator);
                }
                output = outputBuilder.toString();

                break;
            case TEXT:
                output = content[0].trim() + lineSeparator;
                break;
            case TEXT_LABELED:
                output = content[0].trim()
                        + (content.length > 1 ? Constants.CHAR_TAB + content[1].trim() : "")
                        + lineSeparator;
                break;
            case TEXT_MULTILINE:
                output = parseMultilineContent(content[0].trim(), 0);
                break;
            default:
                output = content[0].trim() + lineSeparator;
                break;
        }
        addLine(output);
    }

    /**
     * This method parse the date to a specific format
     *
     * @param date    date
     * @param andTime andTime
     * @return ""
     */
    private static String parseDate(Date date, boolean andTime) {
        if (date == null || date.toString().length() == 0) {
            return "";
        }

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy" + (andTime ? " HH:mm:ss" : ""));
        return dateFormat.format(date);
    }

    /**
     * This method parse the participant
     *
     * @param participant participant
     * @return " ", result
     */
    private static String parseParticipant(ExportModelParticipant participant) {
        ArrayList<String> result = new ArrayList<>();

        if (participant.getFirstname() != null && participant.getFirstname().length() > 0) {
            result.add(participant.getFirstname());
        }

        if (participant.getLastname() != null && participant.getLastname().length() > 0) {
            result.add(participant.getLastname());
        }

        if (participant.getRole() != null && participant.getRole().length() > 0) {
            result.add("(" + participant.getRole() + ")");
        }

        if (participant.getEmail() != null && participant.getEmail().length() > 0) {
            result.add("<" + participant.getEmail() + ">");
        }

        if (participant.getPhone() != null && participant.getPhone().length() > 0) {
            result.add("[" + participant.getPhone() + "]");
        }

        return String.join(" ", result);
    }


    /**
     * This method parse the agenda item
     *
     * @param agendaItem agendaItem
     * @return agendaItem ID + Title
     */
    private static String parseAgendaItemTitle(ExportModelAgendaItem agendaItem) {
        return "[" + agendaItem.getId() + "] " + agendaItem.getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] getContent(ExportModel model) {
        this.lineSeparator = System.getProperty(Constants.JAVA_LINE_SEPARATOR);
        this.content = "";

        ResourceBundle bundle = Controller.getBundle();

        addLine(TextStyle.HEADER,
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_HEADER_DATE),
                parseDate(model.getExportDate(), true),
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_HEADER_CREATION),
                parseDate(model.getCreationDate(), true),
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_HEADER_LAST_EDITION),
                parseDate(model.getLastEditionDate(), true));
        addLine(TextStyle.TITLE, model.getTitle());
        addLine(TextStyle.TEXT_LABELED,
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_MEETING_DATE),
                parseDate(model.getDate(), false));
        addLine(TextStyle.TEXT, model.getLocation());
        addLine(null);
        addLine(TextStyle.SUBTITLE,
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_PARTICIPANTS));
        for (ExportModelParticipant participant : model.getParticipants()) {
            addLine(TextStyle.ITEM, parseParticipant(participant));
        }
        addLine(null);
        addLine(TextStyle.SUBTITLE,
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_AGENDAITEMS));
        for (ExportModelAgendaItem agendaItem : model.getAgendaItems()) {
            addLine(TextStyle.ITEM, parseAgendaItemTitle(agendaItem), agendaItem.getContent());
        }
        addLine(null);
        addLine(TextStyle.TEXT_LABELED,
                ResourceUtil.getLangString(bundle, LanguageKey.EXPORT_NEXT_MEETING),
                parseDate(model.getNextMeeting(), false));

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
