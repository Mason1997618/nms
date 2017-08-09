package cn.edu.uestc.platform.dao;
import java.util.List;

import cn.edu.uestc.platform.pojo.ComplexNode;
import cn.edu.uestc.platform.pojo.Link;

public interface LinkDao {
	
	public void insertLink(Link link); 
	
	public Link getLink(int s_id,String linkName);
	
	public boolean ishaveLinkName(Link link);

	public List<Link> getLinkList(int s_id);

	public void updateLinkStatustoDown(int s_id, String linkName);
	public Link getLinkByPortID(int pt_id);

	public void updateLinkStatusUp(int s_id, String linkName);

	public List<Link> getInnerLink(int cn_id);
	
	public void deleteLinkOnComplexNode(ComplexNode complexNode);
	
	public List<Link> getLinkOnComplexNode(ComplexNode complexNode);

}
