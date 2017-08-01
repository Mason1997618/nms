package cn.edu.uestc.platform.service;

import java.util.List;

import cn.edu.uestc.platform.dao.ComplexNodeDao;
import cn.edu.uestc.platform.dao.ComplexNodeDaoImpl;
import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.pojo.ComplexNode;
import cn.edu.uestc.platform.pojo.Node;

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
		return complexNodeDao.getComplexNodeBys_idAndComplexNodeName(s_id,complexNodeName);
	}

	public List<Node> getInnerNodeList(int s_id, String complexNodeName) {
		// TODO Auto-generated method stub
		ComplexNodeDao complexNodeDao = new ComplexNodeDaoImpl();
		NodeDao nodeDao = new NodeDaoImpl();
		List<Node> nodes = nodeDao.getNodeListByCnid(this.getCompleNode(s_id, complexNodeName).getCn_id());
		return null;
	}

}
