package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.utils.DBUtiles;

public class LinkDaoImpl implements LinkDao {

	/*
	 * 插入链路
	 */
	@Override
	public void insertLink(Link link) {
		// TODO Auto-generated method stub
		String sql = "insert into link(linkName,linkType,linkLength,linkStatus,linkNoise,"
				+ "linkInterference,channelModel,scenario_id,txPortID,rxPortID) " + "values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn;

		try {
			conn = DBUtiles.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, link.getLinkName());
			ps.setInt(2, link.getLinkType());
			ps.setDouble(3, link.getLinkLength());
			ps.setInt(4, link.getLinkStatus());
			ps.setDouble(5, link.getLinkNoise());
			ps.setDouble(6, link.getLinkInterference());
			ps.setInt(7, link.getChannelModel());
			ps.setInt(8, link.getScenario_id());
			ps.setInt(9, link.getTxPort_id());
			ps.setInt(10, link.getRxPort_id());
			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
