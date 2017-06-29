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
	 * 新建节点
	 */
	public boolean createNode(Node node) {
		NodeDao nodeDao = new NodeDaoImpl();
		 NodeController nodecontroller = new NodeController();
		// 判断同一场景下的节点名称是否重复
		if (nodeDao.haveNodeName(node) == false) {
			// 判断节点的ip地址是否已经存在
			if (nodeDao.isHaveIp(node) == false) {
				// 判断节点类型,根据类型对节点所属场景表的节点计数字段进行自增。
				if (node.getNodeType() == 2) { // 若为复杂节点(0,1均代表简单节点)
					nodeDao.plusNumberComplexNode(node.getS_id());
					//此处需要把数据库的回滚加进去，判断节点类型
					System.out.println("启动云平台虚拟机！");
					nodecontroller.createNode(node.getNodeName(),node.getManageIp(),"vm");
					System.out.println("启动虚拟机成功！");
				} else {
					nodeDao.plusNumberSimpleNode(node.getS_id());
				}
				nodeDao.insertNode(node);
				return true;
			}
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
		//boolean flag = dao.updataNode();
		if(dao.isHaveIp(node)||dao.haveNodeName(node)){ //ip或者nodeName其中一个为true则冲突，返回false
			System.out.println("~~~~~~~~~~~~");
			return false;
		}
		dao.updataNode(node);
		return true;
	}
	
	@Test
	public void  demo2(){
		NodeService service = new NodeService();
		NodeDao dao = new NodeDaoImpl();
		Node node = new Node();
		node.setN_id(19);
		node.setNodeName("编辑节点12");
		node.setManageIp("2.3.4.6");
		node.setFlavorType("big");
		node.setS_id(15);
		System.out.println(dao.haveNodeName(node));//false
		System.out.println(dao.isHaveIp(node));//false
		System.out.println(service.editNode(node));
	}
}
