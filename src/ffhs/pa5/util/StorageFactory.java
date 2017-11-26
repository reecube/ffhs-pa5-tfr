package ffhs.pa5.util;

import ffhs.pa5.model.*;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class StorageFactory {

    private DataFile file;

    public StorageFactory() {
        this.file = null;
    }

    public boolean isInitialized() {
        return file == null;
    }


}
