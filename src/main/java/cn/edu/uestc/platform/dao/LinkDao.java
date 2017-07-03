package cn.edu.uestc.platform.dao;
import cn.edu.uestc.platform.pojo.Link;

public interface LinkDao {
	
	public void insertLink(Link link); 
	
	public Link getLink(int s_id,String linkName);
	
	public boolean ishaveLinkName(Link link);

}
