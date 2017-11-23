package ffhs.pa5;

/**
 * This class contains all constants.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class Constants {
    // resources
    private static final String PATH_RESOURCES = "/ffhs/pa5/resources";

    public static final String FXML_STATISTICS = PATH_RESOURCES + "/statistics.fxml";
    public static final String FXML_GAMEBOARD = PATH_RESOURCES + "/gameBoard.fxml";
    public static final String FXML_START_MENU = PATH_RESOURCES + "/startMenu.fxml";
    public static final String FXML_MAIN = FXML_START_MENU;

    public static final String CSS_MAIN = PATH_RESOURCES + "/main.css";

    public static final String IMAGE_SEED = PATH_RESOURCES + "/seed.png";

    // view
    public static final String VIEW_TITLE = "Ouril";
    public static final int VIEW_WIDTH = 1000;
    public static final int VIEW_HEIGHT = 393;

    // game-view
    public static final int GAME_TURN_DELAY = 100;
}
