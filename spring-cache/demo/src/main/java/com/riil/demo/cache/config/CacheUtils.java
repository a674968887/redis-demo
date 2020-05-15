package com.riil.demo.cache.config;

import com.riil.demo.cache.config.dto.CacheNamespaceDto;
import com.riil.demo.cache.config.dto.CachePropertisDto;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {class description}
 * <br>
 * <p>
 * Create on : 2020/5/14 11:35 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
public class CacheUtils {

    /**
     * 添加ehcache的namespace
     *
     * @param cacheManager
     * @return
     */
    public static CacheManager ehcacheNamespace(final CacheManager cacheManager,
                                                final CachePropertisDto cachePropertisDto) {
        System.out.println("ehcacheNamespace");
        System.out.println(cachePropertisDto);
        List<CacheNamespaceDto> namespace = cachePropertisDto.getNamespace();
        for (int i = 0; i < namespace.size(); i++) {
            CacheNamespaceDto cacheNamespaceConfs = namespace.get(i);
            Cache user = new Cache(new CacheConfiguration(cacheNamespaceConfs.getName(), cacheNamespaceConfs.getTtl())
                    .overflowToDisk(true));
            cacheManager.addCache(user);
        }
        return cacheManager;
    }

    /**
     * 添加redis的namespace
     *
     * @param redisCacheConfiguration
     * @return
     */
    public static Map<String, RedisCacheConfiguration> redisNamespace(
            final RedisCacheConfiguration redisCacheConfiguration, final CachePropertisDto cachePropertisDto) {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        // 对每个缓存名称应用不同的配置，自定义过期时间
        List<CacheNamespaceDto> namespace = cachePropertisDto.getNamespace();
        for (int i = 0; i < namespace.size(); i++) {
            CacheNamespaceDto cacheNamespaceConfs = namespace.get(i);
            String name = cacheNamespaceConfs.getName();
            Integer ttl = cacheNamespaceConfs.getTtl();
            configMap.put(name, redisCacheConfiguration.entryTtl(Duration.ofSeconds(ttl)));
        }
        return configMap;
    }

}
