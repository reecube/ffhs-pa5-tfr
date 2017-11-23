package ffhs.pa5.model.highscore;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * the highscore class. Root Element, used as base for saving in a XML file.
 * stores HighscoreGame objects in a List.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Highscore {

    @XmlElementWrapper(name = "games")
    @XmlElement(name = "game")
    private List<HighscoreGame> games;

    /**
     * constructor
     */
    @SuppressWarnings("unused")
    public Highscore() {
        this.games = new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public void setGames(List<HighscoreGame> games) {
        this.games = games;
    }

    @SuppressWarnings("unused")
    public List<HighscoreGame> getGames() {
        return games;
    }

    void sort() {
        Collections.sort(games);
    }
}

