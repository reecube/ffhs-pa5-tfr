package ffhs.pa5.model;

/**
 * this enum contains all possible turn results
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public enum TurnResult {
    SUCCESS(1),
    PLAYER_OUT_OF_SEEDS(2),
    GAME_OVER(3),
    ERROR_WRONG_SIDE(4),
    ERROR_NO_STRATEGY(5),
    ERROR(6);

    private final int value;

    TurnResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
