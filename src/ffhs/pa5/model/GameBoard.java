package ffhs.pa5.model;

import java.util.Observable;

/**
 * GameBoard (indices):
 * <pre>
 * +--+--+--+--+--+--+
 * | 0| 1| 2| 3| 4| 5|
 * +--+--+--+--+--+--+
 * |11|10| 9| 8| 7| 6|
 * +--+--+--+--+--+--+
 * </pre>
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class GameBoard extends Observable {
    public static final int GAME_RESET_WIDTH = 6;
    public static final int GAME_RESET_FIELDS_SIZE = GAME_RESET_WIDTH * 2;
    public static final int GAME_RESET_SEEDS = 4;
    public static final int GAME_RESET_TOTAL_SEEDS = GAME_RESET_FIELDS_SIZE * GAME_RESET_SEEDS;
    public static final int GAME_RESET_MAX_POINTS = GAME_RESET_TOTAL_SEEDS / 2 - 1;

    private int[] field;

    public int[] getField() {
        return field;
    }

    public void setField(int[] field) {
        this.field = field;
    }

    /**
     * will reset the gameboard
     */
    public void reset() {
        this.field = new int[GAME_RESET_FIELDS_SIZE];
        for (int i = 0; i < field.length; i++) {
            this.field[i] = GAME_RESET_SEEDS;
        }

        // notify the observers for changes
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * will return the seed for the given index or -1 if the index is not valid
     *
     * @param idx the index
     * @return the amount of seeds on that field or -1 if the index is not valid
     */
    public int getSeedsForIndex(int idx) {
        int result;

        if (idx >= 0 && idx < field.length) {
            result = field[idx];
        } else {
            result = -1;
        }

        return result;
    }

    /**
     * will set the amount of seeds on field of the given index, but only if the index is valid
     *
     * @param idx   the index
     * @param seeds the amount of seeds
     */
    public void setSeedsForIndex(int idx, int seeds) {
        if (idx >= 0 && idx < field.length && seeds >= 0) {
            this.field[idx] = seeds;

            // notify the observers for changes
            this.setChanged();
            this.notifyObservers();
        }
    }

    /**
     * will return the start of the index range
     *
     * @param isPlayerA true if the current player is player A
     * @return 0 if player A or GAME_RESET_WIDTH if player B
     */
    public static int getIndexRangeFrom(boolean isPlayerA) {
        return isPlayerA ? 0 : GAME_RESET_WIDTH;
    }

    /**
     * will return the end of the index range
     *
     * @param isPlayerA true if the current player is player A
     * @return (GAME_RESET_WIDTH minus 1) if player A or (GAME_RESET_FIELDS_SIZE minus 1) if player B
     */
    public static int getIndexRangeTo(boolean isPlayerA) {
        // it's 0-based that's the reason for the "-1" before return
        return (isPlayerA ? GAME_RESET_WIDTH : GAME_RESET_FIELDS_SIZE) - 1;
    }

    /**
     * checks if the given index is on the current player's side
     *
     * @param isPlayerA true if the current player is player A
     * @param idx       the index
     * @return true if the index is on the current player's side
     */
    public static boolean isIndexOnPlayerSide(boolean isPlayerA, int idx) {
        return getIndexRangeFrom(isPlayerA) <= idx && getIndexRangeTo(isPlayerA) >= idx;
    }

    /**
     * checks if the current player is out of seeds
     *
     * @param isPlayerA true if the current player is player A
     * @return ture if the player is out of seeds
     */
    public boolean isOutOfSeeds(boolean isPlayerA) {
        boolean result = true;

        int min = isPlayerA ? 0 : GAME_RESET_WIDTH;
        int max = isPlayerA ? GAME_RESET_WIDTH : GAME_RESET_FIELDS_SIZE;

        for (int i = min; i < max; i++) {
            if (field[i] > 0) {
                result = false;
                break;
            }
        }

        return result;
    }

    /**
     * counts all the seeds on the field
     *
     * @return the amount of seeds on the field
     */
    public int countSeedsOnField() {
        int result = 0;

        for (int aField : field) {
            if (aField >= 0) {
                result += aField;
            }
        }

        return result;
    }

    /**
     * moves the seeds on the given index to the depending fields
     *
     * @param player    the player
     * @param isPlayerA true if the current player is player A
     * @param srcIndex  the source index of the turn
     * @return SUCCESS if performed successfully of the depending identifier
     */
    TurnResult moveSeeds(Player player, boolean isPlayerA, int srcIndex) {
        TurnResult result;

        // check if the index is in a valid range or return TurnResult.ERROR_WRONG_SIDE
        if (isIndexOnPlayerSide(isPlayerA, srcIndex)) {

            if (!isOutOfSeeds(isPlayerA)) {

                int amountOfSeeds = getSeedsForIndex(srcIndex);

                if (amountOfSeeds > 0) {
                    result = TurnResult.SUCCESS;

                    setSeedsForIndex(srcIndex, 0);

                    int tmpIndex = 1,
                            tmpSeeds, currIdx;
                    while (amountOfSeeds > 0) {
                        // get the current index
                        currIdx = (srcIndex + tmpIndex) % field.length;

                        // add a seed to the depending field
                        tmpSeeds = getSeedsForIndex(currIdx);
                        if (tmpSeeds >= 0) {
                            tmpSeeds++;

                            if (!isIndexOnPlayerSide(isPlayerA, currIdx) && tmpSeeds == 2) {
                                // eat the seeds if there was only one seed before and it's on the enemy-side
                                player.setSeeds(player.getSeeds() + 1);
                                tmpSeeds = 0;
                            }

                            setSeedsForIndex(currIdx, tmpSeeds);
                        } else {
                            result = TurnResult.ERROR;
                            break;
                        }

                        // prepare for next index
                        tmpIndex++;
                        amountOfSeeds--;

                        // notify the observers for changes
                        setChanged();
                        notifyObservers();
                    }

                    if (result == TurnResult.SUCCESS && countSeedsOnField() <= 2) {
                        result = TurnResult.GAME_OVER;
                    }
                } else {
                    result = TurnResult.ERROR;
                }
            } else {
                result = TurnResult.PLAYER_OUT_OF_SEEDS;
            }

        } else {
            result = TurnResult.ERROR_WRONG_SIDE;
        }

        return result;
    }
}
