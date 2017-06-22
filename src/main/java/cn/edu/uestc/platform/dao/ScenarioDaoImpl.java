package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.utils.DBUtiles;

public class ScenarioDaoImpl implements ScenarioDao {

	@Override
	public boolean haveScenarioName(Scenario scenario) {

		try {
			Connection conn = DBUtiles.getConnection();
			String sql = "select s.s_id from scenario as s where s.project_id=? AND s.scenarioName=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, scenario.getProject_id());
			ps.setString(2, scenario.getScenarioName());
			ResultSet rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean insertScenario(Scenario scenario) {

		String sql = "insert into scenario(scenarioName,scenarioType,project_id)values(?,?,?)";
		Connection conn;
		try {
			conn = DBUtiles.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, scenario.getScenarioName());
			ps.setInt(2, scenario.getScenarioType());
			ps.setInt(3, scenario.getProject_id());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * 根据名称查场景
	 */
	@Override
	public Scenario findByScenarioName(Scenario scenario) {
		// TODO Auto-generated method stub
		try {

			Connection conn = DBUtiles.getConnection();
			String sql = " select *from scenario as s where s.project_id = ? and s.scenarioName=? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, scenario.getProject_id());
			ps.setString(2, scenario.getScenarioName());
			ResultSet rs = ps.executeQuery();

			// 根据名字返回 对象，如果查到了就全部写回对象
			if (rs.next() != false) {
				scenario.setS_id(rs.getInt(1));
				scenario.setScenarioName(rs.getString(2));
				scenario.setNumberNode(rs.getInt(3));
				scenario.setNumberSimpleNode(rs.getInt(4));
				scenario.setNumberComplexNode(rs.getInt(5));
				scenario.setDynamicTopologyFile(rs.getString(6));
				scenario.setScenarioStatus(rs.getInt(7));
				scenario.setScenarioType(rs.getInt(8));
				scenario.setProject_id(rs.getInt(9));
				return scenario;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 未查到返回空对象
		return new Scenario();
	}

	@Override
	public List<Scenario> findAllScenarioByProjectId(int p_id) {
		String sql = "select * from scenario as s where s.project_id=?";
		Connection conn;
		List<Scenario> scenarios = new ArrayList<>();

		try {
			conn = DBUtiles.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Scenario scenario = new Scenario();
				scenario.setS_id(rs.getInt(1));
				scenario.setScenarioName(rs.getString(2));
				scenario.setNumberNode(rs.getInt(3));
				scenario.setNumberSimpleNode(rs.getInt(4));
				scenario.setNumberComplexNode(rs.getInt(5));
				scenario.setDynamicTopologyFile(rs.getString(6));
				scenario.setScenarioStatus(rs.getInt(7));
				scenario.setScenarioType(rs.getInt(8));
				scenario.setProject_id(rs.getInt(9));
				scenarios.add(scenario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scenarios;
	}

}
