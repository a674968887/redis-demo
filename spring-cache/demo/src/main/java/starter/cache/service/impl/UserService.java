package starter.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import starter.cache.service.IUserService;
import starter.cache.dao.IUserDao;
import starter.cache.pojo.User;

import java.util.List;


/**
 * {class description}
 * <br>
 * <p>
 * Create on : 2020/4/21 15:42 <br>
 * </p>
 * <br>
 *
 * @author SongZhiBo <br>
 * -------------------------------------------<br>
 * <br>
 */
@Service
public class UserService implements IUserService {

    @Autowired(required = false)
    IUserDao UserDao;


    /**
     * value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
     * key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
     * condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
     */
    @Cacheable( value = "user")
//    @Cacheable(value = {"res","user","12312"})
//    @RedisCacheCustom
    @Override
    public List<User> getUser() {
        return UserDao.getUser();
    }



    @Cacheable(key = "targetClass", value = "user")
    @Override
    public List<User> getUserRe() {
        return UserDao.getUser();
    }


    /**
     * value：缓存位置名称，不能为空，同上
     * key：缓存的key，默认为空，同上
     * condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
     * allEntries：true表示清除value中的全部缓存，默认为false
     */
    /**
     * 清空
     */
    @CacheEvict(value = "user", allEntries = true)
    @Override
    public void clearCache() {

    }

    /**
     * insert
     *
     * @param name
     * @param addrs
     */
    @CachePut(value = "user")
    @Override
    public void insert(String name, String addrs) {
        System.out.println("insert  --> CachePut   执行");
        UserDao.insert(name, addrs);
    }

    /**
     * insertRe
     *
     * @param name
     * @param addrs
     */
    @CachePut(key = "targetClass",value = "user")
    @Override
    public List<User> insertRe(String name, String addrs) {
        UserDao.insert(name, addrs);
        return UserDao.getUser();
    }

}
