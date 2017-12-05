package ffhs.pa5.model;

/**
 * This enumeration handles the allowed states of a meeting protocol.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public enum State {

    PREPARATION(1),
    MEETING(2),
    ENDING(3),
    CLOSED(4);

    private final int value;

    State(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
