package cn.edu.uestc.platform.service;

import java.util.List;

import cn.edu.uestc.platform.dao.ProjectDao;
import cn.edu.uestc.platform.dao.ProjectDaoImpl;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.User;

public class ProjectService {
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
			return true;
		} else {
			System.out.println("工程名已经存在");
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
