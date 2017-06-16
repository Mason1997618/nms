package cn.edu.uestc.platform.testzk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import cn.edu.uestc.platform.utils.DBUtiles;

public class DatabaseTest {

	/*
	 * 静态sql测试
	 * 
	 */
	@Test
	public void demo1() throws SQLException {

		Connection conn = DBUtiles.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "select * from tabletest";
		ResultSet rs = stmt.executeQuery(sql);

		// 执行插入语句，注意execute而不是executeQuery()
		// String sql = "insert into tabletest(username) values('renmin')";
		// stmt.execute(sql);
		while (rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
		}
		stmt.close();
		conn.close();
	}

	/*
	 * 动态sql测试
	 * 
	 */
	@Test
	public void demo2() throws SQLException {
		Connection conn = DBUtiles.getConnection();
		String sql = "insert into tabletest values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, 100);
		ps.setString(2, "rinima");
		int result = ps.executeUpdate();
		System.out.println(result);

	}
}