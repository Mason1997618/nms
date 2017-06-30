package cn.edu.uestc.platform.service;

import cn.edu.uestc.platform.dao.LinkDao;
import cn.edu.uestc.platform.dao.LinkDaoImpl;
import cn.edu.uestc.platform.pojo.Link;

public class LinkService {
	
	public boolean createLink(Link link){
		LinkDao dao =new LinkDaoImpl();
		dao.insertLink(link);
		return true;
	}

}
