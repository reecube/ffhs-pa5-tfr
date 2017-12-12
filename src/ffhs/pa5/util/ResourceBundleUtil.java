package ffhs.pa5.util;

import ffhs.pa5.Constants;
import ffhs.pa5.model.LanguageKeys;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleUtil {
//TODO @Barbara: Löschen
    public static String getLangString(final ResourceBundle resourceBundle, final LanguageKeys key) {
        if (resourceBundle != null) {
            try {
                return resourceBundle.getString(key.name());
            } catch (final MissingResourceException e) {
                return Constants.INDICATOR_MISSING_KEY;
            }
        }
        return Constants.INDICATOR_MISSING_RESOURCE;
    }
}
