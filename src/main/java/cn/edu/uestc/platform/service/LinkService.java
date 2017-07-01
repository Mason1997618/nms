package cn.edu.uestc.platform.service;

import cn.edu.uestc.platform.controller.LinkController;
import cn.edu.uestc.platform.dao.LinkDao;
import cn.edu.uestc.platform.dao.LinkDaoImpl;
import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.dao.PortDao;
import cn.edu.uestc.platform.dao.PortDaoImpl;
import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Node;

public class LinkService {
	
	/*
	 * 创建链路
	 * 需要更新port表的ip
	 * 需要调用沛华的创建链路函数在云平台创建链路
	 * 从link中拿到两个port的id号 txPort_id对应于fromIp,rxPort_id对应于toIp
	 */
	public boolean createLink(Link link,String fromNodeIP,String toNodeIP){
		//插入链路
		LinkDao linkDao =new LinkDaoImpl();
		linkDao.insertLink(link);
		//更新port表的ip地址
		PortDao portDao = new PortDaoImpl();
		portDao.updatePortIP(link.getTxPort_id(),fromNodeIP);
		portDao.updatePortIP(link.getRxPort_id(),toNodeIP);
		//在云平台创建一条链路,通过port来获取到fronNodeName和toNodeName
		NodeDao nodeDao = new NodeDaoImpl();
		Node fromNode = nodeDao.getNodeByPortId(link.getTxPort_id());
		Node toNode = nodeDao.getNodeByPortId(link.getRxPort_id());
		LinkController controller = new LinkController();
		controller.createLinkMTM(fromNode.getNodeName(), fromNodeIP, toNode.getNodeName(), toNodeIP);
		return true;
	}

}
