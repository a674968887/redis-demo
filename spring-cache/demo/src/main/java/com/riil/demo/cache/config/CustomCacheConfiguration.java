package com.riil.demo.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.lang.reflect.Method;

/**
 * {缓存通用配置模板}
 * <br>
 * <p>
 * Create on : 2020/4/21 16:42 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@Configuration
@EnableCaching
public class CustomCacheConfiguration extends CachingConfigurerSupport {
    @Autowired
    private RedisConnectionFactory connectionFactory;

    /**
     * 在没有指定缓存Key的情况下，key生成策略
     *
     * @return KeyGenerator
     */
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("#" + method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 缓存异常处理
     * 默认的处理方式是直接抛出，如redis如果出现故障连接不上就会报错，这里自定义为伪log日志的形式
     *
     * @return CustomErrorHandler 自定义异常处理
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomErrorHandler();
    }

    /**
     * 自定义CacheResolver实现动态选择CacheManager
     *
     * @return
     */
    @Override
    public CacheResolver cacheResolver() {

//        // 通过ConcurrentMap实现缓存管理器
//        CacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
//
//        //通过Redis实现缓存管理器
//        RedisTemplateCustom redisTemplateCustom = new RedisTemplateCustom();
//        CacheManager cacheManager = null;
//        try {
//            cacheManager = redisTemplateCustom.CacheManager(redisTemplateCustom.redisTemplate(connectionFactory));
//        } catch (UnknownHostException e) {
//            System.out.println(e);
//        }
//
//        CacheManager redisCacheManager = cacheManager;
//        List<CacheManager> list = new ArrayList<>();
//        // 优先读redis缓存
//        list.add(redisCacheManager);
//        // redis缓存读取不到该key时再读取simple缓存
//        list.add(concurrentMapCacheManager);
//        return new CustomCacheResolver(list);
return null;
    }

}
