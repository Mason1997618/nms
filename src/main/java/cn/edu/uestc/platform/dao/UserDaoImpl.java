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
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			String sql = "select * from user where username=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			while (rs.next()) {
				dbuser.setU_id(rs.getInt(1));
				dbuser.setUsername(rs.getString(2));
				dbuser.setPsw(rs.getString(3));
				dbuser.setUserstatus(rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return dbuser;
	}

	/*
	 * 尚未用到的函数
	 */
	@Override
	public List<User> findAllUser() {

		List<User> users = new ArrayList<User>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			Statement stmt = conn.createStatement();
			String sql = "select * from user";
			rs = stmt.executeQuery(sql);
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
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			String sql = "insert into user(username,psw)values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPsw());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtiles.releaseResource(ps, conn);
		}

	}

}
