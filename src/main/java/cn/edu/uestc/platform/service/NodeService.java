package cn.edu.uestc.platform.service;

import java.util.List;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.v3.Region;
import org.openstack4j.model.identity.v3.Service;
import org.openstack4j.model.identity.v3.User;
import org.openstack4j.openstack.OSFactory;

import cn.edu.uestc.platform.controller.NodeController;
import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.utils.Constants;

public class NodeService {
	/*
	 * 查找当前场景下的所有节点
	 */
	public List<Node> findAllNodeByScenarioId(int s_id) {
		NodeDao nodeDao = new NodeDaoImpl();
		return nodeDao.findAllNodeByScenarioId(s_id);
	}

	/*
	 * 新建节点
	 */
	public boolean createNode(Node node) {
		NodeDao nodeDao = new NodeDaoImpl();
		
//		NodeController nodecontroller = new NodeController();
		// 判断同一场景下的节点名称是否重复
		if (nodeDao.haveNodeName(node) == false) {	
			 // 判断节点类型,根据类型对节点所属场景表的节点计数字段进行自增。
			if (node.getNodeType() == 2) { // 若为复杂节点(0,1均代表简单节点)
				nodeDao.plusNumberComplexNode(node.getS_id());
//				nodecontroller.createNode(node.getNodeName(), node.getManageIp(), "vm");
			} else {
				nodeDao.plusNumberSimpleNode(node.getS_id());
			}
			nodeDao.insertNode(node);
			return true;
		}
		return false;
	}

	/*
	 * 创建节点测试
	 */
	@Test
	public void demo1() {
		NodeController controller = new NodeController();
		controller.createNode("tianyu123", "192.168.8.321", "vm");
	}
}
