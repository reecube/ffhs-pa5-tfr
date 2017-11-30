package ffhs.pa5;

import ffhs.pa5.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This is the main class
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class Main extends Application {

    /**
     * This is the main function
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new View();
    }
}
