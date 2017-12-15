package ffhs.pa5.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class CustomExclusionStrategy implements ExclusionStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return !f.getDeclaringClass().getName().startsWith(ffhs.pa5.Main.class.getPackage().getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
