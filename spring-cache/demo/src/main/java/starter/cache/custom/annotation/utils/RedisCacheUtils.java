package starter.cache.custom.annotation.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * {class description}
 * <br>
 * <p>
 * Create on : 2020/5/6 17:54 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@Component
public class RedisCacheUtils {
    /**
     * StringRedisTemplate StringRedisTemplate
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public <T> void saveCache(String key, T t,long timeout) {
        System.out.println(RedisCacheUtils.class.getName() + "   start");
        String json = JSONObject.toJSONString(t);
        stringRedisTemplate.opsForValue().set(key,json);

        System.out.println(RedisCacheUtils.class.getName() + "   end");
    }



}
