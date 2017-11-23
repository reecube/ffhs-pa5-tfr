package ffhs.pa5.model.highscore;

import ffhs.pa5.model.Game;
import ffhs.pa5.model.Player;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * HighscoreGame class. The XML file consists of objects of this class.
 * Includes both players, winner
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class HighscoreGame implements Comparable<HighscoreGame> {
    @XmlElement
    private HighscorePlayer playerA;
    @XmlElement
    private HighscorePlayer playerB;
    @XmlElement
    private int winner;

    /**
     * constructor<br><br>
     *
     * attention: noargs-constructor is required because it's a xml object!
     */
    @SuppressWarnings("unused")
    public HighscoreGame() {
        this.playerA = null;
        this.playerB = null;
        this.winner = 0;
    }

    /**
     * constructor to get data from a game
     *
     * @param game the game
     */
    public HighscoreGame(Game game) {
        Player gamePlayerA = game.getPlayerA();
        this.playerA = new HighscorePlayer(
                gamePlayerA.getName(),
                gamePlayerA.getSeeds(),
                gamePlayerA.getComputerStrategy() != null
        );

        Player gamePlayerB = game.getPlayerB();
        this.playerB = new HighscorePlayer(
                gamePlayerB.getName(),
                gamePlayerB.getSeeds(),
                gamePlayerB.getComputerStrategy() != null
        );

        refreshWinner();
    }

    @SuppressWarnings("unused")
    public HighscorePlayer getPlayerA() {
        return playerA;
    }

    @SuppressWarnings("unused")
    public void setPlayerA(HighscorePlayer playerA) {
        this.playerA = playerA;
    }

    @SuppressWarnings("unused")
    public HighscorePlayer getPlayerB() {
        return playerB;
    }

    @SuppressWarnings("unused")
    public void setPlayerB(HighscorePlayer playerB) {
        this.playerB = playerB;
    }

    @SuppressWarnings("unused")
    public int getWinner() {
        return winner;
    }

    @SuppressWarnings("unused")
    public void setWinner(int winner) {
        this.winner = winner;
    }

    /**
     * refreshes the winner
     *
     * @return the winner
     */
    private int refreshWinner() {
        if (playerA != null && playerB != null) {
            if (playerA.getScore() > playerB.getScore()) {
                this.winner = 1;
            } else if (playerA.getScore() < playerB.getScore()) {
                this.winner = 2;
            } else {
                this.winner = 0;
            }
        } else {
            if (playerA != null) {
                this.winner = 1;
            } else if (playerB != null) {
                this.winner = 2;
            }  else {
                this.winner = 0;
            }
        }

        return winner;
    }

    /**
     * returns the player depending on the winner id
     *
     * @return the winner player
     */
    public HighscorePlayer getWinnerPlayer() {
        HighscorePlayer result = null;

        switch (winner) {
            case 1:
                result = playerA;
                break;
            case 2:
                result = playerB;
                break;
        }

        return result;
    }

    @Override
    public int compareTo(HighscoreGame compHg) {
        HighscorePlayer player1 = getWinnerPlayer();
        HighscorePlayer player2 = compHg.getWinnerPlayer();

        int score1 = 0;
        int score2 = 0;

        if (player1 != null) {
            score1 = player1.getScore();
        }
        if (player2 != null) {
            score2 = player2.getScore();
        }

        return score2 - score1;
    }
}
