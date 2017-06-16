package cn.edu.uestc.platform.pojo;

import java.util.List;

public class User {

	private int u_id;
	private String username;
	private String psw;
	private int userstatus;
	private List<Project> projects;
	
	
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public int getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(int userstatus) {
		this.userstatus = userstatus;
	}
	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", username=" + username + ", psw=" + psw + ", userstatus=" + userstatus + "]";
	}
	
	
}
