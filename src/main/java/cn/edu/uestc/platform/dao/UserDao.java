package cn.edu.uestc.platform.dao;

import java.util.List;
import cn.edu.uestc.platform.pojo.User;

public interface UserDao {

	
	//根据用户名查
	public User findByUserName(User user);
	
	public List<User> findAllUser();
	
	public void insertUser(User user);


}
