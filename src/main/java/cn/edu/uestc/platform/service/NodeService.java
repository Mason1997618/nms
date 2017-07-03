package cn.edu.uestc.platform.service;

import java.util.List;

import org.junit.Test;

import cn.edu.uestc.platform.controller.NodeController;
import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.pojo.Node;

public class NodeService {

	/*
	 * 查找当前场景下的所有节点
	 */
	public List<Node> findAllNodeByScenarioId(int s_id) {
		NodeDao nodeDao = new NodeDaoImpl();
		return nodeDao.findAllNodeByScenarioId(s_id);
	}

	/*
	 * 新建节点，先在云平台启动成功 再插入数据库
	 */
	public boolean createNode(Node node) {
		NodeDao nodeDao = new NodeDaoImpl();
		NodeController nodecontroller = new NodeController();
		// 判断同一场景下的节点名称是否重复
		if (nodeDao.haveNodeName(node) == false) {
			// 判断节点的ip地址是否已经存在
			if (nodeDao.isHaveIp(node) == false) {
				// 判断节点类型,根据类型对节点所属场景表的节点计数字段进行自增。
				if (node.getNodeType() == 2) { // 若为复杂节点则启动“vm”(0,1均代表简单节点docker，2代表复杂节点vm)
					// 如果在此处出现错误咋办？(例如用户输入的ip地址已经存在)
					nodecontroller.createNode(node.getNodeName(), node.getManageIp(), "vm");
					// 更新此节点对应场景的节点数量
					nodeDao.plusNumberComplexNode(node.getS_id());
					// 此处需要把数据库的回滚加进去，判断节点类型
					System.out.println("启动vm虚拟机成功！");
				} else {
					nodecontroller.createNode(node.getNodeName(), node.getManageIp(), "docker");
					System.out.println("启动docker成功！");
					nodeDao.plusNumberSimpleNode(node.getS_id());
				}
				nodeDao.insertNode(node);
				return true;
			}
			System.out.println("IP地址冲突！");
		}
		System.out.println("节点名冲突！");
		return false;
	}

	/*
	 * 根据n_id查节点
	 */
	public Node getNode(int n_id) {
		// TODO Auto-generated method stub
		NodeDao dao = new NodeDaoImpl();
		return dao.getNodeByNodeId(n_id);
	}

	/*
	 * 根据节点名和s_id查节点
	 */
	public Node getNodeBynodeName(String nodeName, int s_id) {
		// TODO Auto-generated method stub
		NodeDao dao = new NodeDaoImpl();
		return dao.getNodeBynodeName(nodeName, s_id);
	}

	/*
	 * 编辑节点
	 */
	public boolean editNode(Node node) {
		// TODO Auto-generated method stub
		NodeDao dao = new NodeDaoImpl();
		// boolean flag = dao.updataNode();
		if (dao.isHaveIp(node) || dao.haveNodeName(node)) { // ip或者nodeName其中一个为true则冲突，返回false
			System.out.println("~~~~~~~~~~~~");
			return false;
		}
		dao.updataNode(node);
		return true;
	}
}
