package cn.edu.uestc.platform.service;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.platform.controller.LinkController;
import cn.edu.uestc.platform.controller.NodeController;
import cn.edu.uestc.platform.dao.ComplexNodeDao;
import cn.edu.uestc.platform.dao.ComplexNodeDaoImpl;
import cn.edu.uestc.platform.dao.DeleteDao;
import cn.edu.uestc.platform.dao.DeleteDaoImpl;
import cn.edu.uestc.platform.dao.LinkDao;
import cn.edu.uestc.platform.dao.LinkDaoImpl;
import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.dao.PortDao;
import cn.edu.uestc.platform.dao.PortDaoImpl;
import cn.edu.uestc.platform.pojo.ComplexNode;
import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Port;

public class ComplexNodeService {

	public boolean createComplexNode(ComplexNode complexNode) {
		// TODO Auto-generated method stub
		ComplexNodeDao complexNodeDao = new ComplexNodeDaoImpl();
		NodeDao nodeDao = new NodeDaoImpl();
		if (complexNodeDao.isHaveComplexNodeName(complexNode) == false) {
			// 插入复杂节点
			complexNodeDao.insertComplexNode(complexNode);
			// 更新场景节点统计信息
			nodeDao.plusNumberComplexNode(complexNode.getS_id());
			return true;
		}
		return false;
	}

	public List<ComplexNode> selectComplexNodeList(int s_id) {
		// TODO Auto-generated method stub
		ComplexNodeDao complexNodeDao = new ComplexNodeDaoImpl();

		return complexNodeDao.selectComplexNodeList(s_id);
	}

	public ComplexNode getCompleNode(int s_id, String complexNodeName) {
		// TODO Auto-generated method stub
		ComplexNodeDao complexNodeDao = new ComplexNodeDaoImpl();
		return complexNodeDao.getComplexNodeBys_idAndComplexNodeName(s_id, complexNodeName);
	}

	public List<Node> getInnerNodeList(int s_id, String complexNodeName) {
		// TODO Auto-generated method stub
		ComplexNodeDao complexNodeDao = new ComplexNodeDaoImpl();
		NodeDao nodeDao = new NodeDaoImpl();
		List<Node> nodes = nodeDao.getNodeListByCnid(this.getCompleNode(s_id, complexNodeName).getCn_id());
		return null;
	}

	public boolean deleteComplexNodeService(ComplexNode complexNode) {
		// TODO Auto-generated method stub
		// 拿到所有的链路
		// 删除Openstack上的链路,和数据库中的链路
		LinkDao linkDao = new LinkDaoImpl();
		List<Link> links = linkDao.getInnerLink(complexNode.getCn_id());
		DeleteDao deleteDao = new DeleteDaoImpl();
		// LinkController linkController = new LinkController();
		// 遍历删除内部链路
		for (Link link : links) {
			// linkController.delLinkMTM(link.getFromNodeName(),
			// link.getFromNodeIP(), link.getToNodeName(),
			// link.getToNodeIP());
			deleteDao.deleteLink(link);
		}

		links = linkDao.getLinkOnComplexNode(complexNode);
		// 遍历删除外部链路
		for (Link link : links) {
			// linkController.delLinkMTM(link.getFromNodeName(),
			// link.getFromNodeIP(), link.getToNodeName(), link.getToNodeIP());
			deleteDao.deleteLink(link);
		}

		// 拿到所有节点
		// 删除Openstack上的节点和数据库的节点
		NodeDao nodeDao = new NodeDaoImpl();
		List<Node> nodes = nodeDao.getNodeListByCnid(complexNode.getCn_id());
		// NodeController nodeController = new NodeController();
		PortDao portDao = new PortDaoImpl();
		List<Port> ports = new ArrayList<>();
		for (Node node : nodes) {
			// nodeController.deleteNode(node.getUuid());
			deleteDao.deleteSimpleNode(node);
			ports = portDao.getPortList(node.getN_id());
			for (Port port : ports) {
				portDao.deletePort(port);
			}
		}
		// 删除复杂节点
		ComplexNodeDao complexNodeDao = new ComplexNodeDaoImpl();
		complexNodeDao.deleteComplexNode(complexNode);

		return true;
	}

}
