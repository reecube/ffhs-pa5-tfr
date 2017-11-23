package ffhs.pa5.tests;

/**
 * this class contains tools that can be used in other test classes
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class TestingTools {

    /**
     * Invert the field values to solve the following problem:
     * <p>
     * Java-Array:
     * +--+--+--+--+--+--+
     * | 0| 1| 2| 3| 4| 5|
     * +--+--+--+--+--+--+
     * | 6| 7| 8| 9|10|11|
     * +--+--+--+--+--+--+
     * <p>
     * GameBoard-Array:
     * +--+--+--+--+--+--+
     * | 0| 1| 2| 3| 4| 5|
     * +--+--+--+--+--+--+
     * |11|10| 9| 8| 7| 6|
     * +--+--+--+--+--+--+
     *
     * @param theInvertedField an array with an odd length (not tested in the function, may throw error)
     * @return the inverted array of the given array as explained in description
     * @throws Exception the exception
     */
    public static int[] invertField(int[] theInvertedField) throws Exception {
        int originalField[] = theInvertedField.clone();
        int offsetField = theInvertedField.length / 2;
        for (int i = 0; i < offsetField; i++) {
            theInvertedField[offsetField + i] = originalField[theInvertedField.length - i - 1];
        }
        return theInvertedField;
    }
}
