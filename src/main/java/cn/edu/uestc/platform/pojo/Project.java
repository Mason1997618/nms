package cn.edu.uestc.platform.pojo;

import java.util.List;

public class Project {

	private int p_id;
	private String projectName;
	private int projectStatus;
	private int user_id;
	private List<Scenario> Scenarios;
	
	
	public List<Scenario> getScenarios() {
		return Scenarios;
	}
	public void setScenarios(List<Scenario> scenarios) {
		Scenarios = scenarios;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public int getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	@Override
	public String toString() {
		return "Project [p_id=" + p_id + ", projectName=" + projectName + ", projectStatus=" + projectStatus
				+ ", user_id=" + user_id + "]";
	}
	
	
}
