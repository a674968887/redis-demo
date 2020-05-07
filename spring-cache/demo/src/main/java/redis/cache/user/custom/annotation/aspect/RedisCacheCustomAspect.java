package redis.cache.user.custom.annotation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.cache.user.custom.annotation.utils.RedisCacheUtils;

/**
 * {class description}
 * <br>
 *
 * <p>
 * Create on : 2020/5/6 17:47 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@Aspect
@Component
public class RedisCacheCustomAspect {
    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @Pointcut("@annotation(redis.cache.user.custom.annotation.RedisCacheCustom)")
    public void setJoinPoint(){}

    //环绕通知:可以获取返回值
    @Around(value = "setJoinPoint()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint){
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
            //返回通知
            //缓存至Redis
            Object[] args = proceedingJoinPoint.getArgs();
            //key策略：需要缓存的对象的全类名-id，如：entity.User-1
            String key = result.getClass().getName();

            redisCacheUtils.saveCache(key,result,0);
        } catch (Throwable e) {
            //异常通知
            System.out.println("execute erro："+e);
        }
        //后置通知
        return result;
    }
}
