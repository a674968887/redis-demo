package starter.cache.config;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * {自定义缓存异常处理}
 * <br>
 *
 * <p>
 * 默认的缓存异常处理会抛出异常，导致系统错误，作为解决系统压力的模块不应该出现这样的异常处理方式
 * <p>
 * Create on : 2020/5/7 16:46 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
public class CustomErrorHandler implements CacheErrorHandler {

    @Override
    public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
        System.err.println(key + ":" + e.getMessage());
    }

    @Override
    public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object value) {
        System.err.println(value + ":" + e.getMessage());
    }

    @Override
    public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
        System.err.println(key + ":" + e.getMessage());
    }

    @Override
    public void handleCacheClearError(RuntimeException e, Cache cache) {
        System.err.println(e.getMessage());
    }
}
