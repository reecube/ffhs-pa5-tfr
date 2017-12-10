package ffhs.pa5.view;

import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

/**
 * The View class. Shows the stage and the first scene.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class View extends Stage implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        // TODO: implement this
        System.out.println(o);
        System.out.println(arg);
    }
}
