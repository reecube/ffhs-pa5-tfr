package ffhs.pa5.model;

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

    private String hostName;
    private String username;

    public User() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            this.hostName = addr.getHostName();
        } catch (Exception ex) {
            ex.printStackTrace();

            this.hostName = HOSTNAME_UNKNOWN;
        }
        try {
            this.username = System.getProperty("user.name");
        } catch (Exception ex) {
            ex.printStackTrace();

            this.username = USERNAME_UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return hostName + "\\" + username;
    }
}
