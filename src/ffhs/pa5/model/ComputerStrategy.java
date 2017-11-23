package ffhs.pa5.model;

import javax.xml.bind.annotation.XmlTransient;

/**
 * This interface is made to implement the computer strategies.<br>
 * Just override the depending functions. Like this, you're free to have as many strategies as you want.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
@XmlTransient
public interface ComputerStrategy {

    /**
     * This is the core of the computer strategy.<br>
     * This function will return the index of the next turn or -1 if there is no next turn.
     *
     * @param field the current field
     * @param idxFrom the first index you can choose
     * @param idxTo the last index you can choose
     * @return the index of the turn you want to play or "-1" if there is no valid move.
     */
    int getNextTurnForGameBoard(int[] field, int idxFrom, int idxTo);
}
