package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
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
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

	/*
	 * 根据场景s_id和链路名称查找链路
	 */
	@Override
	public Link getLink(int s_id, String linkName) {
		// TODO Auto-generated method stub
		String sql = "select *from link where scenario_id = ? and linkName = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Link link = new Link();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.setString(2, linkName);
			rs = ps.executeQuery();
			while (rs.next()) {
				link.setL_id(rs.getInt(1));
				link.setLinkName(rs.getString(2));
				link.setLinkType(rs.getInt(3));
				link.setLinkLength(rs.getDouble(4));
				link.setLinkStatus(rs.getInt(5));
				link.setLinkNoise(rs.getDouble(6));
				link.setLinkInterference(rs.getDouble(7));
				link.setChannelModel(rs.getInt(8));
				link.setScenario_id(rs.getInt(9));
				link.setTxPort_id(rs.getInt(10));
				link.setRxPort_id(rs.getInt(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return link;
	}

	/*
	 * 同一场景下的链路名是否存在
	 */
	@Override
	public boolean ishaveLinkName(Link link) {
		// TODO Auto-generated method stub

		String sql = "select *from link as l where l.linkName = ? and l.scenario_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, link.getLinkName());
			ps.setInt(2, link.getScenario_id());
			rs = ps.executeQuery();
			if(rs.next()==false){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return true;
	}

}
