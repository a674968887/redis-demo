package com.riil.demo.cache.config;

import com.riil.demo.cache.config.dto.CachePropertisDto;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.HashMap;
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
        cachePropertisDto.getNamespace().forEach(namespace -> cacheManager.addCache(new Cache(new CacheConfiguration(namespace.getName(), namespace.getTtl())
                .overflowToDisk(true))));
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
        cachePropertisDto.getNamespace().forEach(namespace -> configMap.put(namespace.getName(), redisCacheConfiguration.entryTtl(Duration.ofSeconds(namespace.getTtl()))));
        return configMap;
    }

}
