package starter.cache.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/5/7 13:38 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@Configuration
@EnableCaching
public class CustomCacheResolver implements CacheResolver {

    @Nullable
    private List<CacheManager> cacheManagerList;

    public CustomCacheResolver() {
    }

    public CustomCacheResolver(List<CacheManager> cacheManagerList) {
        this.cacheManagerList = cacheManagerList;
    }

    public List<CacheManager> getCacheManagerList() {
        return cacheManagerList;
    }

    public void setCacheManagerList(@Nullable List<CacheManager> cacheManagerList) {
        this.cacheManagerList = cacheManagerList;
    }

    /**
     * 自定义CacheResolver实现动态选择CacheManager
     * 这里的作用目前不太清晰，目前可以清晰的是配合CacheConfiguration中的cacheResolver可以指定备用的后端缓存
     *
     * @param context
     * @return
     */
    @Override
    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> context) {
        Collection<String> cacheNames = getCacheNames(context);
        if (cacheNames == null) {
            return Collections.emptyList();
        }
        Collection<Cache> result = new ArrayList<>();
        for (CacheManager cacheManager : getCacheManagerList()) {
            for (String cacheName : cacheNames) {
                Cache cache = cacheManager.getCache(cacheName);
                if (cache == null) {
                    throw new IllegalArgumentException("Cannot find cache named '" +
                            cacheName + "' for " + context.getOperation());
                }
                result.add(cache);
            }
        }
        return result;
    }

    /**
     * @param context
     * @return
     */
    private Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return context.getOperation().getCacheNames();
    }
}
