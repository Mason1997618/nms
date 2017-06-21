package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.User;
import cn.edu.uestc.platform.utils.DBUtiles;

public class UserDaoImpl implements UserDao {

	@Override
	public User findByUserName(User user) {
		// TODO Auto-generated method stub
		User dbuser = new User();
		try {
			Connection conn = DBUtiles.getConnection();
			String sql = "select * from user where username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dbuser.setU_id(rs.getInt(1));
				dbuser.setUsername(rs.getString(2));
				dbuser.setPsw(rs.getString(3));
				dbuser.setUserstatus(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dbuser;
	}

	@Override
	public List<User> findAllUser() {

		List<User> users = new ArrayList<User>();
		try {
			Connection conn = DBUtiles.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from user";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				User user = new User();
				user.setU_id(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPsw(rs.getString(3));
				user.setUserstatus(rs.getInt(4));
				users.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtiles.getConnection();
			String sql = "insert into user(username,psw)values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPsw());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 根据工程名查工程
	 * (待修改....)
	 */
	@Override
	public Project findByProjectName(Project project) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtiles.getConnection();
			String sql = "select p.* from project as p left join user as u on u.u_id=p.user_id where p.projectName=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, project.getProjectName());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if ("".equals(rs.getString(2))) { //如果projectname字段为null 说明名字未被占用
					return project;
				} else {
					project.setP_id(rs.getInt(1));
					project.setProjectName(rs.getString(2));
					project.setProjectStatus(rs.getInt(3));
					project.setUser_id(rs.getInt(4));
					return new Project();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}

	@Override
	public boolean insertProject(Project project) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DBUtiles.getConnection();
			String sql = "insert into project(projectName,user_id)values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, project.getProjectName());
			ps.setInt(2, project.getUser_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
