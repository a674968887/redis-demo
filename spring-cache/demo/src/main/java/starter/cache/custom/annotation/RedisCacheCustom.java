package starter.cache.custom.annotation;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/5/6 17:41 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */


import java.lang.annotation.*;

/**
 * 自定义注解，结合AOP实现Redis自动缓存
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface RedisCacheCustom {
}
