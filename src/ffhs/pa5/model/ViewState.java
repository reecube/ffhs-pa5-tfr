package ffhs.pa5.model;

import java.util.Arrays;

/**
 * This class contains all the values of the current view state.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class ViewState {
    private int[] values;
    private String nameA;
    private int seedsA;
    private String nameB;
    private int seedsB;
    private boolean currentPlayerA;
    private int currentTurn;

    /**
     * The legendary constructor.
     *
     * @param values         the values
     * @param nameA          the name of player A
     * @param seedsA         the amount of seeds of player A
     * @param nameB          the name of player B
     * @param seedsB         the amount of seeds of player B
     * @param currentPlayerA if the current player is player A
     * @param currentTurn    the current turn
     */
    public ViewState(int[] values, String nameA, int seedsA, String nameB, int seedsB, boolean currentPlayerA, int currentTurn) {
        this.values = values;
        this.nameA = nameA;
        this.seedsA = seedsA;
        this.nameB = nameB;
        this.seedsB = seedsB;
        this.currentPlayerA = currentPlayerA;
        this.currentTurn = currentTurn;
    }

    public int[] getValues() {
        return values;
    }

    public String getNameA() {
        return nameA;
    }

    public int getSeedsA() {
        return seedsA;
    }

    public String getNameB() {
        return nameB;
    }

    public int getSeedsB() {
        return seedsB;
    }

    public boolean isCurrentPlayerA() {
        return currentPlayerA;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    @Override
    public String toString() {
        return '[' + Arrays.toString(values) + ']';
    }
}
