package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.edu.uestc.platform.pojo.Scenario;
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
	public void demo2(){
//		ScenarioDao sDao =new ScenarioDaoImpl();
//		Scenario scenario = new Scenario();
//		scenario.setScenarioName("scenariotest");
//		scenario.setProject_id(7);
//		System.out.println(sDao.haveScenarioName(scenario));
//		ScenarioDao sDao =new ScenarioDaoImpl();
//		Scenario scenario = new Scenario();
//		scenario.setScenarioName("scenariotest");
//		scenario.setScenarioType(2);
//		scenario.setProject_id(6);
//		sDao.insertScenario(scenario);
		ScenarioDao sDao =new ScenarioDaoImpl();
		Scenario scenario = new Scenario();
		scenario.setScenarioName("scenariotest");
		scenario.setProject_id(8);
		System.out.println(sDao.findByScenarioName(scenario));
		
	}
}
