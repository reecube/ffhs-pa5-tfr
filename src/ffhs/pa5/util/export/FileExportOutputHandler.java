package ffhs.pa5.util.export;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */

public abstract class FileExportOutputHandler implements ExportOutputHandler {

    private String path;

    protected FileExportOutputHandler (String path){
        this.path = path;
        //TODO > Was braucht es sonst noch?
    }


    //TODO > Wie muss ich die von IntelliJ gemeldeten Fehler korrigieren? Darf ich abstract einfach löschen?
    /**
     * TODO
     * @return  TODO
     */
    public abstract static String getFileExtension(){ //TODO > Welche Parameter?
        //TODO > File Extension auslesen
        String fileExtension = "";
        return fileExtension;
    }

    @Override
    public void handleExport(ExportModel eModel) {
        //TODO
    }
}
