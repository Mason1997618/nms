package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Port;
import cn.edu.uestc.platform.utils.DBUtiles;

public class PortDaoImpl implements PortDao {

	/*
	 * 插入端口
	 */
	@Override
	public void insertPort(Port port) {
		// TODO Auto-generated method stub
		String sql = "insert into port(portName,portType,antennaType,portStatus,"
				+ "antennaGain,txPower,modulationScheme,channelCodingScheme,frequencyBandwidth,txBitRate,txPacketLoss,node_id,portIP) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, port.getPortName());
			ps.setInt(2, port.getPortType());
			ps.setInt(3, port.getAntennaType());
			ps.setInt(4, port.getPortStatus());
			ps.setDouble(5, port.getAntennaGain());
			ps.setDouble(6, port.getTxPower());
			ps.setInt(7, port.getModulationScheme());
			ps.setInt(8, port.getChannelCodingScheme());
			ps.setDouble(9, port.getFrequencyBandwidth());
			ps.setDouble(10, port.getTxBitRate());
			ps.setDouble(11, port.getTxPacketLoss());
			ps.setInt(12, port.getN_id());
			ps.setString(13, port.getPortIp());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

	/*
	 * 根据节点n_id返回所有端口
	 */
	@Override
	public List<Port> getPortList(int n_id) {
		// TODO Auto-generated method stub
		String sql = "select *from port as p where p.node_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Port> ports = new ArrayList<Port>();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, n_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Port port = new Port();
				port.setPt_id(rs.getInt(1));
				port.setPortName(rs.getString(2));
				port.setAntennaType(rs.getInt(3));
				port.setPortType(rs.getInt(4));
				port.setPortStatus(rs.getInt(5));
				port.setAntennaGain(rs.getDouble(6));
				port.setTxPower(rs.getDouble(7));
				port.setModulationScheme(rs.getInt(8));
				port.setChannelCodingScheme(rs.getInt(9));
				port.setFrequencyBandwidth(rs.getDouble(10));
				port.setTxBitRate(rs.getDouble(11));
				port.setTxPacketLoss(rs.getDouble(12));
				port.setN_id(rs.getInt(13));
				port.setPortIp(rs.getString(14));
				ports.add(port);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return ports;
	}

	/*
	 * 根据场景s_id和节点名查询到节点所包含的port
	 */
	@Override
	public List<Port> getPortListBynodeName(int s_id, String nodeName) {
		// TODO Auto-generated method stub
		String sql = "select *from port as p join (select *from node as n where n.nodeName=? "
				+ "and n.scenario_id = ?) as n on p.node_id=n.n_id;";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Port> ports = new ArrayList<Port>();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, nodeName);
			ps.setInt(2, s_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Port port = new Port();
				port.setPt_id(rs.getInt(1));
				port.setPortName(rs.getString(2));
				port.setAntennaType(rs.getInt(3));
				port.setPortType(rs.getInt(4));
				port.setPortStatus(rs.getInt(5));
				port.setAntennaGain(rs.getDouble(6));
				port.setTxPower(rs.getDouble(7));
				port.setModulationScheme(rs.getInt(8));
				port.setChannelCodingScheme(rs.getInt(9));
				port.setFrequencyBandwidth(rs.getDouble(10));
				port.setTxBitRate(rs.getDouble(11));
				port.setTxPacketLoss(rs.getDouble(12));
				port.setN_id(rs.getInt(13));
				port.setPortIp(rs.getString(14));
				ports.add(port);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return ports;
	}

	/*
	 * 更新端口IP,以及端口状态
	 */
	@Override
	public void updatePortIP(int Port_id, String IP) {
		// TODO Auto-generated method stub
		String sql = "update port as p set p.portIP = ?,p.portStatus = ? where p.pt_id=?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, IP);
			ps.setInt(2, 1);
			ps.setInt(3, Port_id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

	}

	/*
	 * 通过链路查链路两端的portID,返回一个长度为2的整型数组。
	 */
	@Override
	public int[] getPortIdsOnSameLinkByPort(Port port) {
		// TODO Auto-generated method stub
		String sql = "select txPortID,rxPortID from link where txPortID =? or rxPortID =?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int portId[] = new int[2];
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, port.getPt_id());
			ps.setInt(2, port.getPt_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				portId[0] = rs.getInt(1);
				portId[1] = rs.getInt(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return portId;
	}

	@Override
	public List<Port> getPortByLink(Link link) {
		String sql = "select *from port where pt_id = ? or pt_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Port> ports = new ArrayList<Port>();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, link.getTxPort_id());
			ps.setInt(2, link.getRxPort_id());
			rs = ps.executeQuery();
			while (rs.next()) {
				Port port = new Port();
				port.setPt_id(rs.getInt(1));
				port.setPortName(rs.getString(2));
				port.setAntennaType(rs.getInt(3));
				port.setPortType(rs.getInt(4));
				port.setPortStatus(rs.getInt(5));
				port.setAntennaGain(rs.getDouble(6));
				port.setTxPower(rs.getDouble(7));
				port.setModulationScheme(rs.getInt(8));
				port.setChannelCodingScheme(rs.getInt(9));
				port.setFrequencyBandwidth(rs.getDouble(10));
				port.setTxBitRate(rs.getDouble(11));
				port.setTxPacketLoss(rs.getDouble(12));
				port.setN_id(rs.getInt(13));
				port.setPortIp(rs.getString(14));
				ports.add(port);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ports;
	}

	@Override
	public Port getPortByID(int pt_id) {
		// TODO Auto-generated method stub
		String sql = "select *from port where pt_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Port port = new Port();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pt_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				port.setPt_id(rs.getInt(1));
				port.setPortName(rs.getString(2));
				port.setAntennaType(rs.getInt(3));
				port.setPortType(rs.getInt(4));
				port.setPortStatus(rs.getInt(5));
				port.setAntennaGain(rs.getDouble(6));
				port.setTxPower(rs.getDouble(7));
				port.setModulationScheme(rs.getInt(8));
				port.setChannelCodingScheme(rs.getInt(9));
				port.setFrequencyBandwidth(rs.getDouble(10));
				port.setTxBitRate(rs.getDouble(11));
				port.setTxPacketLoss(rs.getDouble(12));
				port.setN_id(rs.getInt(13));
				port.setPortIp(rs.getString(14));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}

		return port;
	}

	@Override
	public List<Port> getportListByPortIDs(int[] portID) {
		String sql = "select *from port where pt_id = ? or pt_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Port> ports = new ArrayList<Port>();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, portID[0]);
			ps.setInt(2, portID[1]);
			rs = ps.executeQuery();
			while (rs.next()) {
				Port port = new Port();
				port.setPt_id(rs.getInt(1));
				port.setPortName(rs.getString(2));
				port.setAntennaType(rs.getInt(3));
				port.setPortType(rs.getInt(4));
				port.setPortStatus(rs.getInt(5));
				port.setAntennaGain(rs.getDouble(6));
				port.setTxPower(rs.getDouble(7));
				port.setModulationScheme(rs.getInt(8));
				port.setChannelCodingScheme(rs.getInt(9));
				port.setFrequencyBandwidth(rs.getDouble(10));
				port.setTxBitRate(rs.getDouble(11));
				port.setTxPacketLoss(rs.getDouble(12));
				port.setN_id(rs.getInt(13));
				port.setPortIp(rs.getString(14));
				ports.add(port);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ports;
	}

}
