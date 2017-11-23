package ffhs.pa5.model.highscore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The HighscorePlayer class. The XML file consists of objects of this class.
 * Includes player name, score, if player is COM
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class HighscorePlayer {

    @XmlElement
    private String name;
    @XmlElement
    private int score;
    @XmlElement
    private boolean computer;
    private int rank;

    /**
     * constructor<br><br>
     *
     * attention: noargs-constructor is required because it's a xml object!
     */
    @SuppressWarnings("unused")
    public HighscorePlayer() {
        this.name = null;
        this.score = 0;
        this.computer = false;
        this.rank = 0;
    }

    /**
     * constructor
     *
     * @param name     the name
     * @param seeds    the score
     * @param computer is computer
     */
    HighscorePlayer(String name, int seeds, boolean computer) {
        this.name = name;
        this.score = seeds;
        this.computer = computer;
        this.rank = 0;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public int getScore() {
        return score;
    }

    @SuppressWarnings("unused")
    public void setScore(int score) {
        this.score = score;
    }

    @SuppressWarnings("unused")
    public boolean isComputer() {
        return computer;
    }

    @SuppressWarnings("unused")
    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    @SuppressWarnings("unused")
    public int getRank() {
        return rank;
    }

    @SuppressWarnings("unused")
    public void setRank(int rank) {
        this.rank = rank;
    }
}
