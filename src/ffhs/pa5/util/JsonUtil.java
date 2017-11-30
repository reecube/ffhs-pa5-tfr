package ffhs.pa5.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.util.Date;

public abstract class JsonUtil {

    private static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new CustomDateDeserializer());
        return gsonBuilder.create();
    }

    public static <T> T parse(String json, Class<T> classOfT) throws JsonSyntaxException {
        Gson gson = getGson();
        return gson.fromJson(json, classOfT);
    }

    public static String stringify(Object src) {
        Gson gson = getGson();
        return gson.toJson(src);
    }
}
