package ffhs.pa5.util;

import com.google.gson.*;

import java.util.Date;

/**
 * TODO
 *
 * @author Murat Tokmak
 * @author Barbara Fauth
 * @author Yves Riedener
 * @version 1.0
 */
public abstract class JsonUtil {

    /**
     * TODO
     *
     * @return TODO
     */
    private static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateDeserializer());
        CustomExclusionStrategy exclusionStrategy = new CustomExclusionStrategy();
        gsonBuilder.addSerializationExclusionStrategy(exclusionStrategy);
        gsonBuilder.addDeserializationExclusionStrategy(exclusionStrategy);
        return gsonBuilder.create();
    }

    /**
     * TODO
     *
     * @param json     TODO
     * @param classOfT TODO
     * @param <T>      TODO
     * @return TODO
     * @throws JsonSyntaxException TODO
     */
    public static <T> T parse(String json, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = getGson();
        return gson.fromJson(json, classOfT);
    }

    /**
     * TODO
     *
     * @param src TODO
     * @return TODO
     */
    public static String stringify(Object src) {
        Gson gson = getGson();
        return gson.toJson(src);
    }
}
