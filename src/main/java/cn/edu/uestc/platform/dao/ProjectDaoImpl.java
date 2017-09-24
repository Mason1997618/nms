package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.User;
import cn.edu.uestc.platform.utils.DBUtiles;

public class ProjectDaoImpl implements ProjectDao {

	/*
	 * 判断项目名称是否存在 存在返回true 存在返回true 不存在返回false
	 */
	@Override
	public boolean haveProjectName(Project project) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			String sql = "select p.p_id from project as p where p.user_id=? AND p.projectName=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, project.getUser_id());
			ps.setString(2, project.getProjectName());
			rs = ps.executeQuery();
			if (rs.next() == false) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return true;
	}

	/*
	 * 根据项目名称返回项目对象,不存在该项目就返回空对象 注：查对象的时候需要project中有user_id才行
	 */
	@Override
	public Project findByProjectName(Project project) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			String sql = " select *from project as p where p.user_id = ? and  p.projectName=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, project.getUser_id());
			ps.setString(2, project.getProjectName());
			rs = ps.executeQuery();
			// 根据名字返回 对象，如果查到了就全部写回对象
			if (rs.next() != false) {
				project.setP_id(rs.getInt(1));
				project.setProjectName(rs.getString(2));
				project.setProjectStatus(rs.getInt(3));
				project.setUser_id(rs.getInt(4));
				return project;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		// 未查到返回空对象
		return new Project();
	}

	/*
	 * 插入项目
	 */
	public boolean insertProject(Project project) {
		String sql = "insert into project(projectName,user_id)values(?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, project.getProjectName());
			ps.setInt(2, project.getUser_id());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

		return true;
	}

	/*
	 * 根据uid查所有工程
	 */
	public List<Project> findAllProjectByUserId(User user) {
		String sql = "select * from project as p where p.user_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Project> projects = new ArrayList<>();

		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getU_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setP_id(rs.getInt(1));
				project.setProjectName(rs.getString(2));
				project.setProjectStatus(rs.getInt(3));
				project.setUser_id(rs.getInt(4));
				projects.add(project);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return projects;
	}

	/*
	 * 修改工程名
	 */
	@Override
	public void updataProjectName(Project project) {
		// TODO Auto-generated method stub
		String sql = " update project set projectName=? where p_id=?";
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, project.getProjectName());
			ps.setInt(2, project.getP_id());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

}
