package cn.edu.uestc.platform.dao;

import cn.edu.uestc.platform.pojo.Project;

public interface ProjectDao {
	
	
	public boolean haveProjectName(Project project);
	
	public Project findByProjectName(Project project);
	
	public boolean insertProject(Project project);
	
	public void updataProjectName(Project project);

}
