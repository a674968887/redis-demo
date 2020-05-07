//package redis.cache.user.config;
//
//import org.springframework.cache.Cache;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.BasicOperation;
//import org.springframework.cache.interceptor.CacheOperationInvocationContext;
//import org.springframework.cache.interceptor.CacheResolver;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.reflect.Method;
//import java.util.Collection;
//
///**
// * {class description}
// * <br>
// *
// * <p>
// * Create on : 2020/5/7 13:38 <br>
// * </p>
// * <br>
// *
// * @author SongZhiBo <br>
// * -------------------------------------------<br>
// * <br>
// */
//@Configuration
//@EnableCaching
//public class RedisCacheResolerCustom implements CacheResolver {
//    @Override
//    public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> cacheOperationInvocationContext) {
//        Object[] args = cacheOperationInvocationContext.getArgs();
//        Method method = cacheOperationInvocationContext.getMethod();
//        BasicOperation operation = cacheOperationInvocationContext.getOperation();
//        Object target = cacheOperationInvocationContext.getTarget();
//        return null;
//    }
//
//}
