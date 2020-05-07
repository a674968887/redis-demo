package redis.cache.user.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

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
//@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {


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

}
