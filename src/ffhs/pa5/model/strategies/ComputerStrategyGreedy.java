package ffhs.pa5.model.strategies;

import ffhs.pa5.model.ComputerStrategy;

/**
 * This computer strategy will play very aggressive and try to move all seeds from his side.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class ComputerStrategyGreedy implements ComputerStrategy {

    @Override
    public int getNextTurnForGameBoard(int[] field, int idxFrom, int idxTo) {
        int result = -1;
        int best = -1;

        for (int i = idxFrom; i <= idxTo; i++) {
            int tmp = i + field[i];
            if (tmp > i && tmp > best) {
                result = i;
                best = tmp;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "COM Greedy";
    }
}
