package ffhs.pa5.model.strategies;

import ffhs.pa5.model.ComputerStrategy;
import ffhs.pa5.model.GameBoard;

/**
 * This computer strategy will play very deffensive and try to store all seeds on his side.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class ComputerStrategyProtective implements ComputerStrategy {

    @Override
    public int getNextTurnForGameBoard(int[] field, int idxFrom, int idxTo) {
        int result = -1;
        int best = GameBoard.GAME_RESET_TOTAL_SEEDS + 1;

        for (int i = idxFrom; i <= idxTo; i++) {
            int tmp = i + field[i];
            if (tmp > i && tmp < best) {
                result = i;
                best = tmp;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "COM Protective";
    }
}
