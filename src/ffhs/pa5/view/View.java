package ffhs.pa5.view;

import ffhs.pa5.Constants;
import ffhs.pa5.controller.Controller;
import ffhs.pa5.util.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The View class. Shows the stage and the first scene.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class View extends Stage {

    /**
     * Loads the first scene (Menu) into the stage.
     */
    public View() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constants.FXML_MAIN));
            fxmlLoader.setController(new Controller());

            Scene primaryScene = new Scene(fxmlLoader.load(), Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);


            primaryScene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

            setTitle(Constants.VIEW_TITLE);
            setScene(primaryScene);
            setResizable(false);
            sizeToScene();
            show();
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);
        }
    }
}
