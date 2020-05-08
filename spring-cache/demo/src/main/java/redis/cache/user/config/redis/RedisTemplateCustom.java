package redis.cache.user.config.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/4/25 20:18 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
//@ConditionalOnProperty(value = "spring.cache.type",havingValue = "redis")
@Configuration
@EnableCaching
public class RedisTemplateCustom {

    @Bean
    public CacheManager CacheManager(RedisTemplate redisTemplate) {
        //spring cache注解序列化配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                //key序列化方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer()))
                //value序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                //不缓存null值
                .disableCachingNullValues()
                //默认缓存过期时间
                .entryTtl(Duration.ofSeconds(60));

        // 设置一个初始化的缓存名称set集合
        Set<String> cacheNames = new HashSet<>();
        cacheNames.add("redis/cache/user");

        // 对每个缓存名称应用不同的配置，自定义过期时间
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("user", redisCacheConfiguration.entryTtl(Duration.ofSeconds(120)));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisTemplate.getConnectionFactory())
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                // 注意这两句的调用顺序，要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
        System.out.println("bean  redisCacheManager  upload");
        return redisCacheManager;
    }


    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        // 配置redisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        //给定工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        //{"id":"1","name":"张三","age":18}
        //      om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //json数据带类的名称 //["xxx.xxx.xxx",{"id":"1","name":"张三","age":18}]
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        // key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        // value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        // Hash key序列化
        //        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash value序列化
        //        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        System.out.println("bean  redisTemplate  upload");
        return redisTemplate;
    }

}
