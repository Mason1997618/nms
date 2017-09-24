package cn.edu.uestc.platform.service;


import org.junit.Test;

import cn.edu.uestc.platform.dao.ProjectDao;
import cn.edu.uestc.platform.dao.ProjectDaoImpl;
import cn.edu.uestc.platform.dao.UserDaoImpl;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.pojo.User;

public class ServiceTest {

	
	
	@Test
	public void demo1() {
		UserDaoImpl userdao = new UserDaoImpl();
		User user = new User();
		user.setUsername("renmin");
		user = userdao.findByUserName(user);
		System.out.println(user);
	}
	
	@Test
	public void demo2(){
		ProjectDao projectDao = new ProjectDaoImpl();
		Project project = new Project();
		project.setProjectName("我是你爹");
		project.setUser_id(8);
		project = projectDao.findByProjectName(project);
		System.out.println(project);
	}
	
//	@Test
//	public void demo3(){
//		ServiceImpl service = new ServiceImpl();
//		Scenario scenario = new Scenario();
//		scenario.setScenarioName("scenariotest");
//		scenario.setProject_id(7);
//		scenario.setScenarioType(3);
//		service.createScenario(scenario);
//	}

}
