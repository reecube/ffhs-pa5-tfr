package ffhs.pa5.model.highscore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * The HighscoreXML class.
 * Stores the highscore in an XML and reads the highscore from an XML file.
 * (highscore.xml)
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class HighscoreXML implements Cloneable {

    private final String XML_FILE = "highscore.xml";

    private Highscore highscore;

    /**
     * the legendary constructor
     */
    public HighscoreXML() {
        this.highscore = null;
    }

    /**
     * reads data of the xml file
     *
     * @return true if performed successfully
     */
    public boolean readFromXML() {
        this.highscore = null;

        try {
            File file = new File(XML_FILE);

            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(Highscore.class);
                Unmarshaller un = context.createUnmarshaller();
                this.highscore = (Highscore) un.unmarshal(new FileReader(XML_FILE));

                highscore.sort();
            }
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return highscore != null;
    }

    /**
     * writes a game to the xml file. <br>
     * it's appending by reading and writing the file.
     *
     * @return true if performed successfully
     */
    public boolean writeToXML() {
        boolean result = false;

        try {
            File file = new File(XML_FILE);

            JAXBContext context = JAXBContext.newInstance(Highscore.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(highscore, file);

            result = file.exists();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * deletes the current xml file
     *
     * @return true if performed successfully
     */
    private boolean deleteXML() {
        return new File(XML_FILE).delete();
    }

    /**
     * clears and deletes the highscores
     *
     * @return true if performed successfully
     */
    public boolean clearAndDelete() {
        boolean success = deleteXML();

        if (success) {
            this.highscore = null;
        }

        return success;
    }

    public Highscore getHighscore() {
        return highscore;
    }

    public void setHighscore(Highscore highscore) {
        this.highscore = highscore;
    }

    /**
     * adds a game to the highscore
     *
     * @param game the game
     * @return true if performed successfully
     */
    public boolean addGame(HighscoreGame game) {
        boolean result = false;

        if (highscore != null) {
            List<HighscoreGame> list = highscore.getGames();

            if (list != null) {
                result = list.add(game);
            }

            highscore.sort();
        }

        return result;
    }
}
