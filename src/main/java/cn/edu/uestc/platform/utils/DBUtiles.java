package cn.edu.uestc.platform.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtiles {
	static String url = "jdbc:mysql://localhost:3306/networksimulation";
	static String username = "root";
	static String password = "root";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public static void releaseResource(PreparedStatement ps, Connection conn) {
		if (null != ps) {
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	public static void releaseResource(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e2) {
			}
		if (ps != null)
			try {
				ps.close();
			} catch (Exception e2) {
			}
		if (conn != null)
			try {
				conn.close();
			} catch (Exception e2) {
			}
	}
}
