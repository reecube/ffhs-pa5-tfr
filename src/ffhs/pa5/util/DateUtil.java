package ffhs.pa5.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Provides some helper functions for dates.
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class DateUtil {

    /**
     * TODO
     *
     * @param date TODO
     * @return TODO
     */
    public static LocalDate toLocalDate(Date date) {
        return toLocalDate(date, null);
    }

    /**
     * TODO
     *
     * @param date         TODO
     * @param defaultValue TODO
     * @return TODO
     */
    public static LocalDate toLocalDate(Date date, LocalDate defaultValue) {
        if (date == null) {
            return defaultValue;
        }

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
