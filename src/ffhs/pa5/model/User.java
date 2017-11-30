package ffhs.pa5.model;

import ffhs.pa5.util.Logger;

import java.net.InetAddress;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class User {

    private static final String HOSTNAME_UNKNOWN = "Unknown";
    private static final String USERNAME_UNKNOWN = "Unknown";

    private static User instance = null;

    private String hostName;
    private String username;

    /**
     * Initializes the object with the hostname and with the username.
     */
    private User() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            this.hostName = addr.getHostName();
        } catch (Exception ex) {
            Logger logger = Logger.getInstance();
            logger.handleException(ex);

            this.hostName = HOSTNAME_UNKNOWN;
        }
        try {
            this.username = System.getProperty("user.name");
        } catch (Exception ex) {
            ex.printStackTrace();

            this.username = USERNAME_UNKNOWN;
        }
    }

    /**
     * Singleton
     *
     * @return new instance or the existing one if one is existing
     */
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }

        return instance;
    }

    @Override
    public String toString() {
        return hostName + "\\" + username;
    }
}
