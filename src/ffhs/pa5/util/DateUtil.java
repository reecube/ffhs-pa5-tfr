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
     * Util the local date
     *
     * @param date TODO
     * @return TODO
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * The Date from local
     *
     * @param date TODO
     * @return TODO
     */
    public static Date fromLocalDate(LocalDate date) {
        if (date == null) {
            return null;
        }

        return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
