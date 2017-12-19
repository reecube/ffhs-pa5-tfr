package ffhs.pa5;

import ffhs.pa5.controller.Controller;
import ffhs.pa5.util.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            List<String> args = getParameters().getRaw();
            String startupPath = null;

            if (args.size() > 0) {
                startupPath = args.get(0);
            }

            new Controller(startupPath);
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);

            System.exit(1);
        }
    }
}
