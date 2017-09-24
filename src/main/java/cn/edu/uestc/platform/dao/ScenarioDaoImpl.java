package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.utils.DBUtiles;

public class ScenarioDaoImpl implements ScenarioDao {

	@Override
	public boolean haveScenarioName(Scenario scenario) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			String sql = "select s.s_id from scenario as s where s.project_id=? AND s.scenarioName=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scenario.getProject_id());
			ps.setString(2, scenario.getScenarioName());
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return true;
	}

	/*
	 * 插入场景
	 */
	@Override
	public boolean insertScenario(Scenario scenario) {

		String sql = "insert into scenario(scenarioName,scenarioType,project_id,numberNode,numberSimpleNode,numberComplexNode,scenarioStatus)values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, scenario.getScenarioName());
			ps.setInt(2, scenario.getScenarioType());
			ps.setInt(3, scenario.getProject_id());
			ps.setInt(4, scenario.getNumberNode());
			ps.setInt(5, scenario.getNumberSimpleNode());
			ps.setInt(6, scenario.getNumberComplexNode());
			ps.setInt(7, scenario.getScenarioStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

		return true;
	}

	/*
	 * 根据名称查场景
	 */
	@Override
	public Scenario findByScenarioName(Scenario scenario) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			String sql = " select *from scenario as s where s.project_id = ? and s.scenarioName=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, scenario.getProject_id());
			ps.setString(2, scenario.getScenarioName());
			rs = ps.executeQuery();
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

	/*
	 * 返回项目下的所有场景列表
	 */
	@Override
	public List<Scenario> findAllScenarioByProjectId(int p_id) {
		String sql = "select * from scenario as s where s.project_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Scenario> scenarios = new ArrayList<>();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, p_id);
			rs = ps.executeQuery();
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
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return scenarios;
	}

	/*
	 * 删除场景
	 */
	@Override
	public void deletescenario(int s_id) {
		// TODO Auto-generated method stub
		String sql = "delete from scenario where s_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

	}

	/*
	 * 更新场景状态toDown
	 * 
	 * @see cn.edu.uestc.platform.dao.ScenarioDao#updateScenarioStatus(int)
	 */
	@Override
	public void updateScenarioStatusToDown(int s_id) {
		// TODO Auto-generated method stub
		String sql = "update scenario set scenarioStatus = 1 where s_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

	}

	/*
	 * 更新场景状态toUp
	 * 
	 * @see cn.edu.uestc.platform.dao.ScenarioDao#updateScenarioStatusToUp(int)
	 */
	@Override
	public void updateScenarioStatusToUp(int s_id) {
		// TODO Auto-generated method stub
		String sql = "update scenario set scenarioStatus = 0 where s_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

	@Override
	public void insertDynamicTopologyFile(String path, int s_id) {
		// TODO Auto-generated method stub
		String sql = "update scenario set dynamicTopologyFile = ? where s_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, path);
			ps.setInt(2, s_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

	}

	@Override
	public String findDynamicTopologyFileBySid(int s_id) {
		// TODO Auto-generated method stub
		String sql = "select dynamicTopologyFile from scenario where s_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String FilePath = "";
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				FilePath = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FilePath;
	}
}
