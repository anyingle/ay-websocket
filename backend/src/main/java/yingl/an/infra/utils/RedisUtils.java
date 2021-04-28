package yingl.an.infra.utils;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, String> template;

    public <T> void put(String cacheKey, String key, T value) {
        BoundHashOperations<String, Object, Object> bound = template.boundHashOps(cacheKey);
        bound.put(key, GsonSingleton.toJson(value));
    }

    public <T> T get(String cacheKey, String key, Class<T> clazz) {
        BoundHashOperations<String, String, String> bound = template.boundHashOps(cacheKey);
        String v = bound.get(key);
        if (v != null) {
            return GsonSingleton.fromJson(v, clazz);
        }
        return null;
    }

    public <T> T get(String cacheKey, String key, TypeToken<T> typeToken) {
        BoundHashOperations<String, String, String> bound = template.boundHashOps(cacheKey);
        String v = bound.get(key);
        if (v != null) {
            return GsonSingleton.fromJson(v, typeToken);
        }
        return null;
    }

    public <T> List<T> get(String cacheKey, Class<T> clazz) {
        BoundHashOperations<String, String, String> bound = template.boundHashOps(cacheKey);
        return bound.entries().values().parallelStream().map(x -> GsonSingleton.fromJson(x, clazz)).collect(Collectors.toList());
    }

    public <T> Map<String, T> get(String cacheKey, TypeToken<T> typeToken) {
        BoundHashOperations<String, String, String> bound = template.boundHashOps(cacheKey);
        return bound.entries().keySet().parallelStream().collect(Collectors.toMap(r -> r, r -> get(cacheKey, r, typeToken), (n, o) -> n));
    }

    public void delete(String cacheKey, Object key) {
        BoundHashOperations<String, Object, Object> bound = template.boundHashOps(cacheKey);
        bound.delete(key);
    }

    public void delete(String cacheKey) {
        template.delete(cacheKey);
    }
}