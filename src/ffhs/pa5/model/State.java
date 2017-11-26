package ffhs.pa5.model;

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
