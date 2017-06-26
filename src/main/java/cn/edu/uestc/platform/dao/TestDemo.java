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
	public void demo1() throws SQLException {
		Connection conn = DBUtiles.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from user";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
		}
		stmt.close();
		conn.close();
	}

	@Test
	public void demo2() {
		ScenarioDao sDao = new ScenarioDaoImpl();
		Scenario scenario = new Scenario();
		scenario.setScenarioName("scenariotest");
		scenario.setProject_id(8);
		System.out.println(sDao.findByScenarioName(scenario));

	}

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
//		User user = new User();
//		user.setU_id(1);
//		List<Project> projects = pdao.findAllProjectByUserId(user);
//		for (Project project : projects) {
//			System.out.println(project);
//		}
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
	public void demo6() {
		NodeDao nodeDao = new NodeDaoImpl();
		Node node = new Node();
		node.setNodeName("节点1");
		int s_id = 3;
//		node.setScenario_id(s_id);
		nodeDao.insertNode(node);
		

	}
	@Test
	public void demo7(){
		NodeDao nodeDao = new NodeDaoImpl();
		for(Node node:nodeDao.findAllNodeByScenarioId(4)){
			System.out.println(node);
		}
	}
	
	
	@Test
	public void demo8(){
		NodeDao dao = new NodeDaoImpl();
//		dao.plusNumberComplexNode(2);
		dao.plusNumberSimpleNode(2);
	}
	
	
}
