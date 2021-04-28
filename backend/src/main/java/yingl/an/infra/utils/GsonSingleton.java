package yingl.an.infra.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author Ay.
 */
public class GsonSingleton {

    private static Gson gson = null;

    private GsonSingleton() {
    }

    public static Gson getInstance() {
        if (null == gson) {
            synchronized (GsonSingleton.class) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                // 下换线转驼峰
                gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
                // 显示空值
                gsonBuilder.serializeNulls();
                // 格式化
                gsonBuilder.setPrettyPrinting().create();
                // 奥利给
                gson = gsonBuilder.create();
            }
        }
        return gson;
    }

    public static String toJson(Object object) {
        return getInstance().toJson(object);
    }

    public static <T> T fromJson(String gsonString, Class<T> cls) {
        return getInstance().fromJson(gsonString, cls);
    }

    public static <T> T fromJson(String jsonStr, TypeToken<T> typeToken) {
        return getInstance().fromJson(jsonStr, typeToken.getType());
    }
}