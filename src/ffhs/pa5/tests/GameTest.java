package ffhs.pa5.tests;

import ffhs.pa5.model.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * the tests for the Game class
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class GameTest {
    private Game game;
    private int[] testingGameBoardField;

    // ********************************************************************************
    // useful functions to reduce repetitive code:
    // ********************************************************************************

    /**
     * will initialize the game
     */
    private void initializeGame() {
        this.game = new Game();
        game.initialize();
    }

    /**
     * will reset the game
     *
     * @param startingPlayer the starting player
     * @throws Exception the exception
     */
    private void resetGame(Player startingPlayer) throws Exception {
        game.reset(startingPlayer);

        testingGameBoardField = new int[GameBoard.GAME_RESET_FIELDS_SIZE];
        for (int i = 0; i < testingGameBoardField.length; i++) {
            testingGameBoardField[i] = GameBoard.GAME_RESET_SEEDS;
        }
    }

    /**
     * will reset the game and put it on the defined state
     *
     * @param currentPlayer    the current player
     * @param scorePlayerA     the score of the player A
     * @param scorePlayerB     the score of the player B
     * @param theInvertedField the inverted field
     * @throws Exception the exception
     */
    private void resetGameWithState(Player currentPlayer, int scorePlayerA, int scorePlayerB, int[] theInvertedField) throws Exception {
        resetGame(currentPlayer);

        game.getPlayerA().setSeeds(scorePlayerA);
        game.getPlayerB().setSeeds(scorePlayerB);

        game.getGameBoard().setField(TestingTools.invertField(theInvertedField));
    }

    // ********************************************************************************
    // private test-functions:
    // ********************************************************************************

    /**
     * will test the next-turn-case. <br>
     * just used in tests to have more beautiful code
     *
     * @param testCaseNumber      the number of the test-case
     * @param testCaseDescription the description of the test-case
     * @throws Exception the exception
     */
    private void testNextTurnCase(int testCaseNumber, String testCaseDescription) throws Exception {
        testInitialize();

        System.out.format("testNextTurnCase: test-case %d: %s\n", testCaseNumber, testCaseDescription);
    }

    /**
     * will test the next-turn-if-strategy-exists-case. <br>
     * just used in tests to have more beautiful code
     *
     * @param testCaseNumber      the number of the test-case
     * @param testCaseDescription the description of the test-case
     * @throws Exception the exception
     */
    private void testDoNextTurnIfStrategyExistsCase(int testCaseNumber, String testCaseDescription) throws Exception {
        testInitialize();

        System.out.format("doNextTurnIfStrategyExists: test-case %d: %s\n", testCaseNumber, testCaseDescription);
    }

    /**
     * will compare the gameboard field with the given field
     *
     * @param theField the given field
     * @throws Exception the exception
     */
    private void testGameBoardField(int[] theField) throws Exception {
        assertArrayEquals(theField, game.getGameBoard().getField());
    }

    /**
     * will test the current testing-gameboard-field
     *
     * @throws Exception the exception
     */
    private void testGameBoardField() throws Exception {
        testGameBoardField(testingGameBoardField);
    }

    /**
     * will test the player scores of the current game
     *
     * @param expectedScorePlayerA the expected score for player A
     * @param expectedScorePlayerB the expected score for player B
     * @throws Exception the exception
     */
    private void testPlayerScores(int expectedScorePlayerA, int expectedScorePlayerB) throws Exception {
        assertEquals(expectedScorePlayerA, game.getPlayerA().getSeeds());
        assertEquals(expectedScorePlayerB, game.getPlayerB().getSeeds());
    }

    /**
     * will test all possible first turns to be correctly implemented
     *
     * @param idxStart the starting index
     * @throws Exception the exception
     */
    private void testFirstTurn(int idxStart) throws Exception {
        resetGame(idxStart < GameBoard.GAME_RESET_WIDTH ? game.getPlayerA() : game.getPlayerB());

        // run the function
        game.nextTurn(idxStart);

        // test the result
        for (int i = 0; i < 5; i++) {
            testingGameBoardField[(idxStart + i) % testingGameBoardField.length] = i > 0 ? 5 : 0;
        }
        testGameBoardField();

        // the current player should be playerB, because playerA started
        assertEquals(idxStart < GameBoard.GAME_RESET_WIDTH ? game.getPlayerB() : game.getPlayerA(), game.getCurrentPlayer());

        // the current turn should be 1
        assertEquals(1, game.getCurrentTurn());
    }

    // ********************************************************************************
    // unit-test-functions:
    // ********************************************************************************

    /**
     * will test the initialization of a game
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testInitialize() throws Exception {
        initializeGame();

        assertNotNull(game);
        assertNotNull(game.getGameBoard());
        assertNotNull(game.getPlayerA());
        assertNotNull(game.getPlayerB());
    }

    /**
     * will test the reset of a game
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testReset() throws Exception {
        testInitialize();

        // set some random scores to test the score-reset
        game.getPlayerA().setSeeds(123);
        game.getPlayerB().setSeeds(321);

        // run the resetGame code
        Player startingPlayer = game.getPlayerA();
        resetGame(startingPlayer);

        // check turn information
        assertEquals(0, game.getCurrentTurn());
        assertEquals(startingPlayer, game.getCurrentPlayer());

        // check player a
        assertNotNull(game.getPlayerA().getName());
        assertEquals(0, game.getPlayerA().getSeeds());

        // check player b
        assertNotNull(game.getPlayerB().getName());
        assertEquals(0, game.getPlayerB().getSeeds());

        // check game board
        testGameBoardField();

        // check player scores (should be 0)
        testPlayerScores(0, 0);
    }

    /**
     * will test all the nextTurn-cases to verify if the whole function is working as expected
     *
     * @throws Exception the exception
     */
    @Test
    public void testNextTurn() throws Exception {
        testNextTurnCase01();
        testNextTurnCase02();
        testNextTurnCase03();
        testNextTurnCase04();
        testNextTurnCase05();
        testNextTurnCase06();
        testNextTurnCase07();
        testNextTurnCase08();
        testNextTurnCase09();
        testNextTurnCase10();
    }

    /**
     * test-case: test all possible first turns
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase01() throws Exception {
        testNextTurnCase(1, "test all possible first turns");

        resetGame(game.getPlayerA());
        for (int i = 0; i < testingGameBoardField.length; i++) {
            testFirstTurn(i);
        }
    }

    /**
     * test-case: some basic turns with the expected result
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase02() throws Exception {
        testNextTurnCase(2, "some basic turns with the expected result");

        TurnResult lastResult;
        resetGame(game.getPlayerA());
        lastResult = game.nextTurn(5);
        assertEquals(TurnResult.SUCCESS, lastResult);
        lastResult = game.nextTurn(11);
        assertEquals(TurnResult.SUCCESS, lastResult);
        lastResult = game.nextTurn(1);
        assertEquals(TurnResult.SUCCESS, lastResult);
        lastResult = game.nextTurn(7);
        assertEquals(TurnResult.SUCCESS, lastResult);
        lastResult = game.nextTurn(5);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                6, 0, 6, 6, 5, 0,
                1, 5, 6, 6, 0, 7
        }));
        testPlayerScores(0, 0);
        assertEquals(game.getPlayerB(), game.getCurrentPlayer());
        assertEquals(5, game.getCurrentTurn());
    }

    /**
     * test-case: basic example of the pdf (player A and B are inverted)
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase03() throws Exception {
        testNextTurnCase(3, "basic example of the pdf (player A and B are inverted)");

        TurnResult lastResult;
        resetGame(game.getPlayerB());
        testGameBoardField(TestingTools.invertField(new int[]{
                4, 4, 4, 4, 4, 4,
                4, 4, 4, 4, 4, 4
        }));
        lastResult = game.nextTurn(10);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                5, 5, 5, 4, 4, 4,
                5, 0, 4, 4, 4, 4
        }));
        lastResult = game.nextTurn(5);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                5, 5, 5, 4, 4, 0,
                5, 0, 5, 5, 5, 5
        }));
        lastResult = game.nextTurn(9);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                6, 6, 6, 4, 4, 0,
                6, 1, 0, 5, 5, 5
        }));
        lastResult = game.nextTurn(2);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                6, 6, 0, 5, 5, 1,
                6, 1, 0, 6, 6, 6
        }));
    }

    /**
     * test-case: invalid new turn
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase04() throws Exception {
        testNextTurnCase(4, "invalid new turn");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerA(), 0, 0, new int[]{
                1, 2, 3, 0, 0, 0,
                1, 2, 3, 0, 0, 0
        });
        lastResult = game.nextTurn(9);
        assertEquals(TurnResult.ERROR_WRONG_SIDE, lastResult);
        lastResult = game.nextTurn(4);
        assertEquals(TurnResult.ERROR, lastResult);
    }

    /**
     * test-case: eating-rule example of the pdf (player A and B are inverted)
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase05() throws Exception {
        testNextTurnCase(5, "eating-rule example of the pdf (player A and B are inverted)");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerB(), 0, 0, new int[]{
                6, 6, 0, 5, 5, 1,
                6, 1, 0, 6, 6, 6
        });
        testPlayerScores(0, 0);
        lastResult = game.nextTurn(11);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                7, 7, 1, 6, 6, 0,
                0, 1, 0, 6, 6, 6
        }));
        testPlayerScores(0, 1);
        lastResult = game.nextTurn(4);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                7, 7, 1, 6, 0, 1,
                0, 0, 1, 7, 7, 7
        }));
        testPlayerScores(1, 1);
    }

    /**
     * test-case: check for eating of multiple seeds
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase06() throws Exception {
        testNextTurnCase(6, "check for eating of multiple seeds");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerB(), 0, 0, new int[]{
                1, 1, 5, 1, 1, 0,
                6, 0, 0, 0, 0, 0
        });
        testPlayerScores(0, 0);
        lastResult = game.nextTurn(11);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                0, 0, 6, 0, 0, 1,
                0, 0, 0, 0, 0, 0
        }));
        testPlayerScores(0, 4);
    }

    /**
     * test-case: check for player field empty
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase07() throws Exception {
        testNextTurnCase(7, "check for player field empty");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerA(), 0, 0, new int[]{
                0, 0, 0, 0, 0, 0,
                1, 2, 3, 4, 5, 5
        });
        lastResult = game.nextTurn(0);
        assertEquals(TurnResult.PLAYER_OUT_OF_SEEDS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                0, 0, 0, 0, 0, 0,
                1, 2, 3, 4, 5, 5
        }));
        assertEquals(game.getPlayerB(), game.getCurrentPlayer());
        lastResult = game.nextTurn(6);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                0, 0, 0, 0, 0, 0,
                2, 3, 4, 5, 6, 0
        }));
        assertEquals(game.getPlayerA(), game.getCurrentPlayer());
        lastResult = game.nextTurn(3);
        assertEquals(TurnResult.PLAYER_OUT_OF_SEEDS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                0, 0, 0, 0, 0, 0,
                2, 3, 4, 5, 6, 0
        }));
        assertEquals(game.getPlayerB(), game.getCurrentPlayer());
    }

    /**
     * test-case: check for a large number of seeds on a single spot
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase08() throws Exception {
        testNextTurnCase(8, "check for a large number of seeds on a single spot");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerB(), 0, 0, new int[]{
                0, 0, 0, 0, 0, 0,
                23, 0, 0, 0, 0, 0
        });
        testPlayerScores(0, 0);
        lastResult = game.nextTurn(11);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                0, 0, 0, 0, 0, 0,
                1, 2, 2, 2, 2, 2
        }));
        testPlayerScores(0, 6);
    }

    /**
     * test-case: example of the pdf
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase09() throws Exception {
        testNextTurnCase(9, "example of the pdf");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerB(), 0, 0, new int[]{
                1, 1, 0, 1, 0, 0,
                15, 1, 0, 0, 1, 0
        });
        testPlayerScores(0, 0);
        lastResult = game.nextTurn(11);
        assertEquals(TurnResult.SUCCESS, lastResult);
        testGameBoardField(TestingTools.invertField(new int[]{
                1, 1, 0, 0, 1, 1,
                1, 2, 1, 1, 2, 1
        }));
        testPlayerScores(0, 4);
    }

    /**
     * test-case: check game over
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testNextTurnCase10() throws Exception {
        testNextTurnCase(10, "check game over");

        TurnResult lastResult;
        resetGameWithState(game.getPlayerB(), 11, 3, new int[]{
                1, 1, 0, 0, 0, 0,
                3, 0, 0, 0, 0, 0
        });
        testPlayerScores(11, 3);
        lastResult = game.nextTurn(11);
        assertEquals(TurnResult.GAME_OVER, lastResult);
        assertEquals(game.getPlayerB(), game.getCurrentPlayer());
        assertEquals(0, game.getCurrentTurn());
    }

    /**
     * will test all the doNextTurnIfStrategyExists-cases to verify if the whole function is working as expected
     * <p>
     * =&gt; the individual strategies have own test-classes
     *
     * @throws Exception the exception
     */
    @Test
    public void testDoNextTurnIfStrategyExists() throws Exception {
        testDoNextTurnIfStrategyExistsCase01();
        testDoNextTurnIfStrategyExistsCase02();
    }

    /**
     * test-case: no strategy
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testDoNextTurnIfStrategyExistsCase01() throws Exception {
        testDoNextTurnIfStrategyExistsCase(1, "no strategy");

        TurnResult lastResult;
        game.getPlayerA().setComputerStrategy(null);
        resetGame(game.getPlayerA());
        lastResult = game.doNextTurnIfStrategyExists();
        assertEquals(TurnResult.ERROR_NO_STRATEGY, lastResult);
    }

    /**
     * test-case: own custom strategy
     *
     * @throws Exception the exception
     */
    @org.junit.Test
    public void testDoNextTurnIfStrategyExistsCase02() throws Exception {
        testDoNextTurnIfStrategyExistsCase(2, "own custom strategy");

        TurnResult lastResult;
        Player thePlayer = game.getPlayerA();
        thePlayer.setComputerStrategy((field, idxFrom, idxTo) -> idxFrom);
        resetGame(thePlayer);
        lastResult = game.doNextTurnIfStrategyExists();
        assertEquals(TurnResult.SUCCESS, lastResult);
    }
}