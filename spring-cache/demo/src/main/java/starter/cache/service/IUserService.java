package starter.cache.service;

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
public interface IUserService {
	

	 /**
	  * 获取user
	 * @return
	 */
	 List<User> getUser();

	 /**
	  * 获取user
	 * @return
	 */
	 List<User> getUserRe();

	 /**
	  * 清空
	 */

	 void clearCache();

	 /**
	  * insert
	  */
	 void insert(String name, String addrs);
	 /**
	  * insert
	  */
	 List<User> insertRe(String name, String addrs);
}
