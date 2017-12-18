package ffhs.pa5.util;

import ffhs.pa5.Constants;
import ffhs.pa5.model.type.LanguageKey;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Util the exist Ressources
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class ResourceUtil {

    /**
     * Get the language string
     *
     * @param resourceBundle resourceBundle
     * @param key            key
     * @return Constants
     */
    public static String getLangString(final ResourceBundle resourceBundle, final LanguageKey key) {
        if (resourceBundle == null) {
            return Constants.INDICATOR_MISSING_RESOURCE;
        }

        try {
            return resourceBundle.getString(key.name());
        } catch (final MissingResourceException ex) {
            Logger logger = Logger.getInstance();
            logger.handleException(ex);

            return Constants.INDICATOR_MISSING_KEY;
        }
    }
}
