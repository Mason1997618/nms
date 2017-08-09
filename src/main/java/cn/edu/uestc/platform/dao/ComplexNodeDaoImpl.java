package cn.edu.uestc.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openstack4j.model.trove.builder.DatabaseUserBuilder;

import cn.edu.uestc.platform.pojo.ComplexNode;
import cn.edu.uestc.platform.utils.DBUtiles;

public class ComplexNodeDaoImpl implements ComplexNodeDao {

	/*
	 * 查询复杂节点名是否重复
	 */
	@Override
	public boolean isHaveComplexNodeName(ComplexNode complexNode) {
		// TODO Auto-generated method stub
		String sql = "select *from complexnode where complexnodename = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, complexNode.getComplexNodeName());
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

	@Override
	public void insertComplexNode(ComplexNode complexNode) {
		// TODO Auto-generated method stub
		String sql = "insert into complexnode(complexnodename,x,y,s_id,iconUrl) values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, complexNode.getComplexNodeName());
			ps.setFloat(2, complexNode.getX());
			ps.setFloat(3, complexNode.getY());
			ps.setInt(4, complexNode.getS_id());
			ps.setString(5, complexNode.getIconUrl());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}
	}

	@Override
	public List<ComplexNode> selectComplexNodeList(int s_id) {
		// TODO Auto-generated method stub
		String sql = "select *from complexnode where s_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ComplexNode> complexNodes = new ArrayList<ComplexNode>();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				ComplexNode complexNode = new ComplexNode();
				complexNode.setCn_id(rs.getInt(1));
				complexNode.setComplexNodeName(rs.getString(2));
				complexNode.setX(rs.getFloat(3));
				complexNode.setY(rs.getFloat(4));
				complexNode.setS_id(rs.getInt(5));
				complexNode.setIconUrl(rs.getString(6));
				complexNodes.add(complexNode);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}
		return complexNodes;
	}

	@Override
	public ComplexNode getComplexNodeBys_idAndComplexNodeName(int s_id, String complexNodeName) {
		// TODO Auto-generated method stub
		String sql = "select *from complexnode where s_id = ? and complexNodeName = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ComplexNode complexNode = new ComplexNode();
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, s_id);
			ps.setString(2, complexNodeName);
			rs = ps.executeQuery();
			while (rs.next()) {
				complexNode.setCn_id(rs.getInt(1));
				complexNode.setComplexNodeName(rs.getString(2));
				complexNode.setX(rs.getFloat(3));
				complexNode.setY(rs.getFloat(4));
				complexNode.setS_id(rs.getInt(5));
				complexNode.setIconUrl(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(rs, ps, conn);
		}

		return complexNode;
	}

	@Override
	public void deleteComplexNode(ComplexNode complexNode) {
		// TODO Auto-generated method stub

		String sql = "delete from complexnode where cn_id = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtiles.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, complexNode.getCn_id());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtiles.releaseResource(ps, conn);
		}

	}

}
