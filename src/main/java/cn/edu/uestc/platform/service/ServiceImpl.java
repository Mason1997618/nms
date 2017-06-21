package cn.edu.uestc.platform.service;

import java.util.List;

import cn.edu.uestc.platform.dao.ProjectDao;
import cn.edu.uestc.platform.dao.ProjectDaoImpl;
import cn.edu.uestc.platform.dao.ScenarioDao;
import cn.edu.uestc.platform.dao.ScenarioDaoImpl;
import cn.edu.uestc.platform.dao.UserDao;
import cn.edu.uestc.platform.dao.UserDaoImpl;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.pojo.User;

public class ServiceImpl implements IService {

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
	
	/*
	 * 新建工程
	 */

	public boolean createProject(Project project) {

		ProjectDaoImpl projectdao = new ProjectDaoImpl();
		// 在插入数据之前 要先判断一下工程名是否已存在
		if (projectdao.haveProjectName(project) == false) {
			projectdao.insertProject(project);
			System.out.println("插入工程成功");
			return true;
		} else {
			System.out.println("工程名已经存在");
			return false;
		}
	}
	
	/*
	 * 新建场景
	 * 
	 */
	
	public boolean createScenario(Scenario scenario){
		//在插入数据之前 要先判断一下场景名是否已存在
		ScenarioDao scenarioDao = new ScenarioDaoImpl();
		if(scenarioDao.haveScenarioName(scenario)==false){
			scenarioDao.insertScenario(scenario);
			System.out.println("新建场景成功！");
			return true;
		}else{
			System.out.println("场景名已经存在！");
			return false;
		}
	}
	
	public List<Project> findAllProjectByUserId(User user){
		ProjectDaoImpl projectDao = new ProjectDaoImpl();
		return projectDao.findAllProjectByUserId(user);
		
	}

}
