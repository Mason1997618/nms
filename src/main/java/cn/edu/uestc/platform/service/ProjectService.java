package cn.edu.uestc.platform.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cn.edu.uestc.platform.action.ActionController;
import cn.edu.uestc.platform.dao.ProjectDao;
import cn.edu.uestc.platform.dao.ProjectDaoImpl;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.User;

public class ProjectService {
	private static Logger logger = Logger.getLogger(ProjectService.class);
	/*
	 * 新建工程
	 */
	public boolean createProject(Project project) {

		ProjectDaoImpl projectdao = new ProjectDaoImpl();
		// 在插入数据之前 要先判断一下工程名是否已存在
		if (projectdao.haveProjectName(project) == false) {
			//默认工程一创建就是Enable状态
			project.setProjectStatus(1);
			projectdao.insertProject(project);
			System.out.println("插入工程成功");
			logger.info("插入工程成功"  + project.getProjectName() + "   注册成功，注册时间: " + new Date());
			return true;
		} else {
			logger.info("工程名:"+project.getProjectName()+"已存在");
			return false;
		}
	}
	
	/*
	 * 查找当前用户下的所有工程。
	 */
	public List<Project> findAllProjectByUserId(User user) {
		ProjectDaoImpl projectDao = new ProjectDaoImpl();
		return projectDao.findAllProjectByUserId(user);

	}
	
	/*
	 * 修改工程名
	 */
	
	public boolean updataProjectName(Project project){
		ProjectDao projectDao = new ProjectDaoImpl();
		//修改工程名之前先判断工程名是否重复
		if(projectDao.haveProjectName(project)==false){
			//不重复则修改工程名
			projectDao.updataProjectName(project);
			return true;
		}
		return false;
	}
}
