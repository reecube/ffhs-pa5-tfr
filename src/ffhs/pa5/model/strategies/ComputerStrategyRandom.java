package ffhs.pa5.model.strategies;

import ffhs.pa5.model.ComputerStrategy;

import java.util.Random;

/**
 * This computer strategy will randomly play a field.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class ComputerStrategyRandom implements ComputerStrategy {

    private Random random;

    public ComputerStrategyRandom() {
        this.random = new Random();
    }

    /**
     * returns a random number between the min and max value
     *
     * @param min the min value
     * @param max the max value
     * @return the random number
     */
    private int getRandomNumber(int min, int max) {
        // nextInt is normally exclusive of the top value, so add 1 to make it inclusive
        return random.nextInt((max - min) + 1) + min;
    }

    @Override
    public int getNextTurnForGameBoard(int[] field, int idxFrom, int idxTo) {
        int result = -1;

        int[] validFields = new int[field.length];
        int validFieldsLength = 0;

        // fill the validFields with all the fields over 0
        for (int i = idxFrom; i <= idxTo; i++) {
            if (field[i] > 0) {
                validFields[validFieldsLength] = i;
                validFieldsLength++;
            }
        }

        // just continue if there is at least 1 valid field
        if (validFieldsLength > 0) {
            result = validFields[getRandomNumber(0, validFieldsLength - 1)];
        }

        return result;
    }

    @Override
    public String toString() {
        return "COM Random";
    }
}
