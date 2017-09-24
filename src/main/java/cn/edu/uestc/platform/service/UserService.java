package cn.edu.uestc.platform.service;

import cn.edu.uestc.platform.dao.UserDao;
import cn.edu.uestc.platform.dao.UserDaoImpl;
import cn.edu.uestc.platform.pojo.User;

public class UserService {
	/*
	 * 用户登录
	 */

	public User userLogin(User user) {
		UserDao userdao = new UserDaoImpl();

		return userdao.findByUserName(user);
	}

	/*
	 * 用户注册
	 */
	public boolean userRegister(User user) {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDaoImpl();
		if (userdao.findByUserName(user).getU_id() == 0) {
			userdao.insertUser(user);
			return true;
		} else {
			return false;
		}

	}
}
