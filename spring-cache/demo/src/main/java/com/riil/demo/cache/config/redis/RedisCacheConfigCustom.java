package com.riil.demo.cache.config.redis;

import com.riil.demo.cache.config.CacheUtils;
import com.riil.demo.cache.config.dto.CachePropertisDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Map;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/5/13 15:11 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@ConditionalOnProperty(value = "spring.cache.type", havingValue = "redis")
@Configuration
@EnableCaching
public class RedisCacheConfigCustom {

    /**
     * 默认过期时间
     */
    private static final long S_ENTRY_TTL = 60;
    @Autowired
    CachePropertisDto cachePropertisDto;

    /**
     * 获取spring data redis 中RedisTemplate bean 使用RedisTemplate中定义的序列化
     * 设置其它的默认值
     *
     * @param redisTemplate redisTemplate
     * @return
     */
    @Bean
    protected RedisCacheConfiguration customRedisCacheConfiguration(
            @Qualifier("customRedisTemplate") final RedisTemplate redisTemplate) {
        //spring cache注解序列化配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                //key序列化方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer()))
                //value序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                //不缓存null值
                .disableCachingNullValues()
                //默认缓存过期时间
                .entryTtl(Duration.ofSeconds(S_ENTRY_TTL));
        return redisCacheConfiguration;
    }


    /**
     * 自定义redis
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    protected CacheManager cacheManager(@Qualifier("customRedisTemplate") final RedisTemplate redisTemplate,
                                        @Qualifier("customRedisCacheConfiguration") final RedisCacheConfiguration redisCacheConfiguration) {

        // 设置一个初始化的缓存名称set集合
//        Set<String> cacheNames = new HashSet<>();
//        cacheNames.add("default");
        // 对每个缓存名称应用不同的配置，自定义过期时间
        Map<String, RedisCacheConfiguration> configMap = CacheUtils.redisNamespace(redisCacheConfiguration, cachePropertisDto);
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisTemplate.getConnectionFactory())
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                // 注意这两句的调用顺序，要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        System.out.println("bean  redisCacheManager  upload");
        return redisCacheManager;
    }
}
