package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;

import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.pojo.User;
import cn.edu.uestc.platform.utils.DBUtiles;

public class TestDemo {

	@Test
	public void demo3() {
		UserDao userdao = new UserDaoImpl();
		User user = new User();
		user.setUsername("小赖");

		System.out.println(userdao.findByUserName(user));
	}

	@Test
	public void demo4() {
		ProjectDaoImpl pdao = new ProjectDaoImpl();
		// User user = new User();
		// user.setU_id(1);
		// List<Project> projects = pdao.findAllProjectByUserId(user);
		// for (Project project : projects) {
		// System.out.println(project);
		// }
		Project project = new Project();
		project.setProjectName("updataname");
		project.setP_id(1);
		pdao.updataProjectName(project);
	}

	@Test
	public void demo5() {
		ScenarioDao scenario = new ScenarioDaoImpl();
		System.out.println(scenario.findAllScenarioByProjectId(7));
	}

	@Test
	public void dem9() {
		NodeDao dao = new NodeDaoImpl();
		Node node = new Node();
		node.setManageIp("1.1.1.1");
		System.out.println(dao.isHaveIp(node));
		node.setNodeName("编辑节点12");
		node.setS_id(15);
		System.out.println(dao.haveNodeName(node));
	}

}
