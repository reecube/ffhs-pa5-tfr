package ffhs.pa5.tests;

import ffhs.pa5.model.GameBoard;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * the tests for the GameBoard class
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class GameBoardTest {
    private GameBoard gameBoard;

    // ********************************************************************************
    // useful functions to reduce repetitive code:
    // ********************************************************************************

    /**
     * will initialize the gameboard
     */
    private void initializeGameBoard() {
        this.gameBoard = new GameBoard();
        gameBoard.setField(new int[]{});
    }

    // ********************************************************************************
    // unit-test-functions:
    // ********************************************************************************

    /**
     * will test the initialization of the gameboard
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testInitialize() throws Exception {
        initializeGameBoard();

        assertNotNull(gameBoard);
        assertNotNull(gameBoard.getField());
    }

    /**
     * will test the reset of the gameboard
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testReset() throws Exception {
        testInitialize();

        gameBoard.reset();
        int[] field = gameBoard.getField();
        for (int aField : field) {
            assertEquals(GameBoard.GAME_RESET_SEEDS, aField);
        }
    }

    /**
     * will test the getSeedsForIndex(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetSeedsForIndex() throws Exception {
        testReset();

        int[] field = gameBoard.getField();
        for (int i = 0; i < field.length; i++) {
            assertEquals(field[i], gameBoard.getSeedsForIndex(i));
        }

        // wrong values should be -1
        assertEquals(-1, gameBoard.getSeedsForIndex(Integer.MIN_VALUE));
        assertEquals(-1, gameBoard.getSeedsForIndex(-1));
        assertEquals(-1, gameBoard.getSeedsForIndex(field.length));
        assertEquals(-1, gameBoard.getSeedsForIndex(Integer.MAX_VALUE));
    }

    /**
     * will test the setSeedsForIndex(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testSetSeedsForIndex() throws Exception {
        testGetSeedsForIndex();

        int magicNumber = 13;
        for (int i = 0; i < GameBoard.GAME_RESET_FIELDS_SIZE; i++) {
            gameBoard.setSeedsForIndex(i, magicNumber);
        }

        int[] field = gameBoard.getField();
        for (int aField : field) {
            assertEquals(magicNumber, aField);
        }

        // nothing should happen here
        gameBoard.setSeedsForIndex(0, Integer.MIN_VALUE);
        gameBoard.setSeedsForIndex(0, -1);

        // the first field should still be the magic number
        field = gameBoard.getField();
        assertEquals(magicNumber, field[0]);

        // 0 should be a valid value
        gameBoard.setSeedsForIndex(0, 0);
        field = gameBoard.getField();
        assertEquals(0, field[0]);
    }

    /**
     * will test the getIndexRangeFrom(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetIndexRangeFrom() throws Exception {
        assertEquals(0, GameBoard.getIndexRangeFrom(true));
        assertEquals(GameBoard.GAME_RESET_WIDTH, GameBoard.getIndexRangeFrom(false));
    }

    /**
     * will test the getIndexRangeTo(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testGetIndexRangeTo() throws Exception {
        assertEquals(GameBoard.GAME_RESET_WIDTH - 1, GameBoard.getIndexRangeTo(true));
        assertEquals(GameBoard.GAME_RESET_FIELDS_SIZE - 1, GameBoard.getIndexRangeTo(false));
    }

    /**
     * will test the isIndexOnPlayerSide(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testIsIndexOnPlayerSide() throws Exception {
        // test for the first index and the first index - 1
        // test for the last index and the last index + 1

        // for player A
        assertEquals(false, GameBoard.isIndexOnPlayerSide(true, GameBoard.getIndexRangeFrom(true) - 1));
        assertEquals(true, GameBoard.isIndexOnPlayerSide(true, GameBoard.getIndexRangeFrom(true)));
        assertEquals(true, GameBoard.isIndexOnPlayerSide(true, GameBoard.getIndexRangeTo(true)));
        assertEquals(false, GameBoard.isIndexOnPlayerSide(true, GameBoard.getIndexRangeTo(true) + 1));

        // for player B
        assertEquals(false, GameBoard.isIndexOnPlayerSide(false, GameBoard.getIndexRangeFrom(false) - 1));
        assertEquals(true, GameBoard.isIndexOnPlayerSide(false, GameBoard.getIndexRangeFrom(false)));
        assertEquals(true, GameBoard.isIndexOnPlayerSide(false, GameBoard.getIndexRangeTo(false)));
        assertEquals(false, GameBoard.isIndexOnPlayerSide(false, GameBoard.getIndexRangeTo(false) + 1));
    }

    /**
     * will test the isOutOfSeeds(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testIsOutOfSeeds() throws Exception {
        testReset();

        gameBoard.setField(TestingTools.invertField(new int[]{
                0, 0, 0, 0, 0, 0,
                1, 2, 3, 4, 5, 6
        }));

        assertEquals(true, gameBoard.isOutOfSeeds(true));
        assertEquals(false, gameBoard.isOutOfSeeds(false));

        gameBoard.setField(TestingTools.invertField(new int[]{
                1, 2, 3, 4, 5, 6,
                0, 0, 0, 0, 0, 0
        }));

        assertEquals(false, gameBoard.isOutOfSeeds(true));
        assertEquals(true, gameBoard.isOutOfSeeds(false));
    }

    /**
     * will test the countSeedsOnField(...) function
     *
     * @throws Exception the exception
     */
    @Test
    public void testCountSeedsOnField() throws Exception {
        testReset();

        gameBoard.setField(TestingTools.invertField(new int[]{
                0, 1, 2, 3, 4, 5,
                6, 7, 8, 9, 10, 11
        }));

        assertEquals(66, gameBoard.countSeedsOnField());
    }

    /**
     * will test the nextTurn(...) function<br>
     * this function is already tested by GameTest, because this function is fundamental for the Game class
     *
     * @throws Exception the exception
     */
    @Test
    public void testMoveSeeds() throws Exception {
        // this function is already tested by GameTest, because this function is fundamental for the Game class

        GameTest gameTest = new GameTest();

        gameTest.testNextTurn();
    }
}