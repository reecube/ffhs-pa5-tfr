package ffhs.pa5;

import ffhs.pa5.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * this is the main class
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class Main extends Application {

    /**
     * this is the main function
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