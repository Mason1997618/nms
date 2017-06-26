package cn.edu.uestc.platform.dao;

import java.util.List;

import cn.edu.uestc.platform.pojo.Node;

public interface NodeDao {
	
	
	public void insertNode(Node node);
	public boolean haveNodeName(Node node);
	public List<Node> findAllNodeByScenarioId(int s_id);
	
//	public void plusNumberNode(int s_id); 无论插入简单节点还是复杂节点都会给节点总数+1，所以不用专门写一个给节点计数的函数
	public void plusNumberSimpleNode(int s_id);
	public void plusNumberComplexNode(int s_id);
}
