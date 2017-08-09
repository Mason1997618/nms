package cn.edu.uestc.platform.dao;

import java.util.List;

import cn.edu.uestc.platform.pojo.ComplexNode;

public interface ComplexNodeDao {

	public boolean isHaveComplexNodeName(ComplexNode complexNode);
	public void insertComplexNode(ComplexNode complexNode);
	public List<ComplexNode> selectComplexNodeList(int s_id);
	public ComplexNode getComplexNodeBys_idAndComplexNodeName(int s_id, String complexNodeName);
	public void deleteComplexNode(ComplexNode complexNode);
	
}
