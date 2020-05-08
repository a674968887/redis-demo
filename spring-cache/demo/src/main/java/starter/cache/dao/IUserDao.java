/** 
 */

package starter.cache.dao;

import org.apache.ibatis.annotations.*;
import starter.cache.pojo.User;

import java.util.List;

/**
 * {class description}
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
@Mapper
public interface IUserDao {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "addrs", column = "addrs")
    })
    /**
     * 查看所有
     */
    @Select("select * from user")
    List<User> getUser();

    /**
     * 添加用户
     * @param name
     * @param addrs
     */
    @Insert("insert into user(name, addrs) values(#{name}, #{addrs})")
    void insert(@Param("name") String name, @Param("addrs") String addrs);
}
