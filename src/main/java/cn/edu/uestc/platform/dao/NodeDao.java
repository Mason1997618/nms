package cn.edu.uestc.platform.dao;

import java.util.List;

import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Node;

public interface NodeDao {
	
	
	public void insertNode(Node node);
	public boolean haveNodeName(Node node);
	public List<Node> findAllNodeByScenarioId(int s_id);
	public void plusNumberSimpleNode(int s_id);
	public void plusNumberComplexNode(int s_id);
	public Node getNodeByNodeId(int n_id);
	public Node getNodeBynodeName(String nodeName, int s_id);
	public boolean isHaveIp(Node node);
	public boolean updataNode(Node node);
	public void plusNumberPort(int n_id);
	public Node getNodeByPortId(int txPort_id);
	public Node getNodeByLink(Link link);
}
