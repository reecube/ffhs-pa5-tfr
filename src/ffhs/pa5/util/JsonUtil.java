package ffhs.pa5.util;

import com.google.gson.*;

import java.util.Date;

/**
 * Util Json
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public class JsonUtil {

    private Gson gson;

    /**
     * Initialize the exclusion strategy
     *
     * @param exclusionStrategy TODO
     */
    private void initialize(ExclusionStrategy exclusionStrategy) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateDeserializer());

        if (exclusionStrategy != null) {
            gsonBuilder.addSerializationExclusionStrategy(exclusionStrategy);
            gsonBuilder.addDeserializationExclusionStrategy(exclusionStrategy);
        }

        this.gson = gsonBuilder.create();
    }

    /**
     * Initialize Json util
     */
    public JsonUtil() {
        initialize(new CustomExclusionStrategy());
    }

    /**
     * Initializer Json util by exclusion strategy
     *
     * @param exclusionStrategy TODO
     */
    public JsonUtil(ExclusionStrategy exclusionStrategy) {
        initialize(exclusionStrategy);
    }

    /**
     * Parse Json syntax exception
     *
     * @param json     TODO
     * @param classOfT TODO
     * @param <T>      TODO
     * @return TODO
     * @throws JsonSyntaxException TODO
     */
    public <T> T parse(String json, Class<T> classOfT) throws JsonSyntaxException {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Convert object to string
     *
     * @param src TODO
     * @return TODO
     */
    public String stringify(Object src) {
        return gson.toJson(src);
    }
}
