package ffhs.pa5.factory.export;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.model.type.LanguageKey;
import ffhs.pa5.util.ResourceUtil;
import javafx.stage.FileChooser;

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

        addLine(labelContent + Constants.CHAR_TAB + theContent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected byte[] getContent(ExportModel model) {
        this.lineSeparator = System.getProperty(Constants.JAVA_LINE_SEPARATOR);
        this.content = "";

        addLabelContent(LanguageKey.ERROR_TITLE, model.getTitle());
        addLine(null);

        // TODO: @barbara implement the rest

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
