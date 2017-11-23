package ffhs.pa5.model.strategies;

import ffhs.pa5.model.ComputerStrategy;

/**
 * This computer strategy will always play the field with the least seeds.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class ComputerStrategyMin implements ComputerStrategy {

    @Override
    public int getNextTurnForGameBoard(int[] field, int idxFrom, int idxTo) {
        int result = -1;
        int minValueOverZero = Integer.MAX_VALUE;

        for (int i = idxFrom; i <= idxTo; i++) {
            if (field[i] > 0 && field[i] < minValueOverZero) {
                minValueOverZero = field[i];
                result = i;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "COM Min";
    }
}
