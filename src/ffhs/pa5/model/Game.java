package ffhs.pa5.model;

import ffhs.pa5.model.strategies.*;

import javax.xml.bind.annotation.*;
import java.util.Observable;

/**
 * The legendary Game class.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class Game extends Observable {
    private static final String NAME_PLAYER_A = "Player A";
    private static final String NAME_PLAYER_B = "Player B";

    @XmlElement
    private Player playerA;
    @XmlElement
    private Player playerB;
    private GameBoard gameBoard;
    @XmlElement
    private int currentTurn;
    @XmlAttribute
    private Player currentPlayer;
    private ComputerStrategy[] computerStrategies;
    private boolean over;

    /**
     * The legendary constructor.<br>
     * This will initialize all the variables.<br>
     * It also contains the information, which computer strategies will be available.
     */
    public Game() {
        this.playerA = new Player();
        this.playerB = new Player();
        this.gameBoard = new GameBoard();
        this.currentTurn = 0;
        this.currentPlayer = null;
        this.computerStrategies = new ComputerStrategy[]{
                new ComputerStrategyMin(),
                new ComputerStrategyMax(),
                new ComputerStrategyRandom(),
                new ComputerStrategyGreedy(),
                new ComputerStrategyProtective()
        };
        this.over = false;
    }

    /**
     * Will initialize the game.<br>
     * Currently only sets the names of the players.
     */
    public void initialize() {
        playerA.setName(NAME_PLAYER_A);
        playerB.setName(NAME_PLAYER_B);
    }

    /**
     * Will reset the game: <br>
     * <ul>
     * <li>Resets the gameboard</li>
     * <li>Sets the seeds of both players to 0</li>
     * <li>reset the winner to null</li>
     * <li>Sets the current turn and player</li>
     * </ul>
     *
     * @param refStartingPlayer the player which should start the game
     */
    public void reset(Player refStartingPlayer) {
        // reset the gameBoard and the seeds
        gameBoard.reset();
        playerA.setSeeds(0);
        playerB.setSeeds(0);

        // reset the current turn information
        this.currentTurn = 0;
        this.currentPlayer = refStartingPlayer;
        this.over = false;

        // notify the observers for changes
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Will reset the game with playerA.
     */
    public void reset() {
        reset(playerA);
    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    /**
     * Will do the next turn and check the result.
     *
     * @param srcIndex the index of the field to move
     * @return the result of the turn, will be SUCCESS if everything performed successfully.
     */
    public TurnResult nextTurn(int srcIndex) {
        TurnResult result;
        TurnResult gameBoardResult = gameBoard.moveSeeds(currentPlayer, playerA.equals(currentPlayer), srcIndex);

        switch (gameBoardResult) {
            case SUCCESS:
            case PLAYER_OUT_OF_SEEDS:
                result = TurnResult.SUCCESS;
                break;
            default:
                result = gameBoardResult;
                break;
        }

        if (result == TurnResult.SUCCESS) {
            if (playerA.getSeeds() + playerB.getSeeds() >= GameBoard.GAME_RESET_MAX_POINTS) {

                result = TurnResult.GAME_OVER;
            } else {

                // increment the current turn and change the current player
                this.currentTurn++;
                if (currentPlayer == playerA) {
                    this.currentPlayer = playerB;
                } else {
                    this.currentPlayer = playerA;
                }

                // return the gameBoardResult
                result = gameBoardResult;
            }

            // notify the observers for changes
            this.setChanged();
            this.notifyObservers();
        }

        return result;
    }

    /**
     * Will do the next turn with the strategy and will check the result.
     *
     * @return the result of the turn, will be SUCCESS if everything performed successfully.
     */
    public TurnResult doNextTurnIfStrategyExists() {
        TurnResult result;
        ComputerStrategy computerStrategy = currentPlayer.getComputerStrategy();

        if (computerStrategy != null) {
            int idxFrom = GameBoard.getIndexRangeFrom(currentPlayer == playerA);
            int idxTo = GameBoard.getIndexRangeTo(currentPlayer == playerA);
            int nextTurn = computerStrategy.getNextTurnForGameBoard(gameBoard.getField(), idxFrom, idxTo);

            if (nextTurn >= 0) {
                result = nextTurn(nextTurn);
            } else {
                result = TurnResult.PLAYER_OUT_OF_SEEDS;
            }
        } else {
            result = TurnResult.ERROR_NO_STRATEGY;
        }

        return result;
    }

    public ComputerStrategy[] getComputerStrategies() {
        return computerStrategies;
    }
}
