package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		Connection conn;
		try {
			conn = DBUtiles.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, port.getPortName());
			ps.setInt(2, port.getPortType());
			ps.setInt(3, port.getAntennaType());
			ps.setInt(4,port.getPortStatus());
			ps.setDouble(5,  port.getAntennaGain());
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
		}
	}

	
	/*
	 * 根据节点n_id返回所有端口
	 */
	@Override
	public List<Port> getPortList(int n_id) {
		// TODO Auto-generated method stub
		String sql = "select *from port as p where p.node_id=?";
		Connection conn;
		List<Port> ports=new ArrayList<Port>();
		try {
			conn=DBUtiles.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, n_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
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
