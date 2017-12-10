package ffhs.pa5.util;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class CustomDateDeserializer implements JsonDeserializer<Date> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Date deserialize(JsonElement dateElement, Type typeOfSrc, JsonDeserializationContext context) {
        try {
            return new Date(dateElement.getAsLong());
        } catch (Exception ex) {
            final Logger logger = Logger.getInstance();
            logger.handleException(ex);
        }
        return null;
    }
}
