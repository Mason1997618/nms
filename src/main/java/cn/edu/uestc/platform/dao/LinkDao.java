package cn.edu.uestc.platform.dao;
import java.util.List;

import cn.edu.uestc.platform.pojo.Link;

public interface LinkDao {
	
	public void insertLink(Link link); 
	
	public Link getLink(int s_id,String linkName);
	
	public boolean ishaveLinkName(Link link);

	public List<Link> getLinkList(int s_id);

}
