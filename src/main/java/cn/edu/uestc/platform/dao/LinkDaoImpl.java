package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
				+ "linkInterference,channelModel,scenario_id,txPortID,rxPortID,fromNodeName,toNodeName,fromNodeIP,toNodeIP,cn_id) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			ps.setString(11, link.getFromNodeName());
			ps.setString(12, link.getToNodeName());
			ps.setString(13, link.getFromNodeIP());
			ps.setString(14, link.getToNodeIP());
			ps.setInt(15, link.getCn_id());
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
				link.setFromNodeName(rs.getString(12));
				link.setToNodeName(rs.getString(13));
				link.setFromNodeIP(rs.getString(14));
				link.setToNodeIP(rs.getString(15));
				link.setCn_id(rs.getInt(16));
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
	 * 链路名是否存在
	 */
	@Override
	public boolean ishaveLinkName(Link link) {
		// TODO Auto-generated method stub
		String sql = "select *from link where linkName = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, link.getLinkName());
			rs = ps.executeQuery();
			System.out.println("执行了");
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
	 * 返回对应场景下的所有链路
	 */
	@Override
	public List<Link> getLinkList(int s_id) {
		// TODO Auto-generated method stub
		String sql = "select *from link where scenario_id = ?";
		List<Link> links = new ArrayList<Link>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Link link = new Link();
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
				link.setFromNodeName(rs.getString(12));
				link.setToNodeName(rs.getString(13));
				link.setFromNodeIP(rs.getString(14));
				link.setToNodeIP(rs.getString(15));
				link.setCn_id(rs.getInt(16));
				links.add(link);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return links;
	}

	/*
	 * 链路挂起
	 */
	@Override
	public void updateLinkStatustoDown(int s_id, String linkName) {
		String sql = "update link set linkStatus = 1 where scenario_id=? and linkName = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.setString(2, linkName);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

	/*
	 * 通过portID拿到link
	 */

	@Override
	public Link getLinkByPortID(int pt_id) {
		String sql = "select *from link where txPortID=? or rxPortID = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Link link = new Link();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pt_id);
			ps.setInt(2, pt_id);
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
				link.setFromNodeName(rs.getString(12));
				link.setToNodeName(rs.getString(13));
				link.setFromNodeIP(rs.getString(14));
				link.setToNodeIP(rs.getString(15));
				link.setCn_id(rs.getInt(16));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return link;
	}

	@Override
	public void updateLinkStatusUp(int s_id, String linkName) {
		String sql = "update link set linkStatus = 0 where scenario_id=? and linkName = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.setString(2, linkName);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

}
