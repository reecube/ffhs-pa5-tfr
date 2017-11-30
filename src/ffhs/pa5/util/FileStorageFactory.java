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
public class FileStorageFactory {

    private DataFile file;

    public FileStorageFactory() {
        this.file = null;
    }

    private boolean isInitialized() {
        return file == null;
    }

    public DataFile newFile() throws Exception {
        throw new Exception("Not implemented yet!");
    }

    public DataFile readFile(String path) throws Exception {
        throw new Exception("Not implemented yet!");
    }

    public boolean writeFile(String path) throws Exception {
        if (!this.isInitialized()) {
            throw new Exception("The file has not been initialized yet!");
        }

        throw new Exception("Not implemented yet!");
    }


}
