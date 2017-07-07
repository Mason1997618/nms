package cn.edu.uestc.platform.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.uestc.platform.controller.LinkController;
import cn.edu.uestc.platform.dao.DeleteDao;
import cn.edu.uestc.platform.dao.DeleteDaoImpl;
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
	 * 创建链路 先在openstack上成功创建了链路 再将信息插入数据库。 需要更新port表的ip 需要调用沛华的创建链路函数在云平台创建链路
	 * 从link中拿到两个port的id号 txPort_id对应于fromIp,rxPort_id对应于toIp
	 */
	public boolean createLink(Link link, String fromNodeIP, String toNodeIP) {

		LinkDao linkDao = new LinkDaoImpl();
		if (linkDao.ishaveLinkName(link) == false) {
			// 在云平台创建一条链路,通过port来获取到fronNodeName和toNodeName
			NodeDao nodeDao = new NodeDaoImpl();
			Node fromNode = nodeDao.getNodeByPortId(link.getTxPort_id());
			Node toNode = nodeDao.getNodeByPortId(link.getRxPort_id());
//			LinkController controller = new LinkController();
//			controller.createLinkMTM(fromNode.getNodeName(), fromNodeIP, toNode.getNodeName(), toNodeIP);

			// 插入链路
			linkDao.insertLink(link);
			// 更新port表的ip地址,以及链路状态
			PortDao portDao = new PortDaoImpl();
			portDao.updatePortIP(link.getTxPort_id(), fromNodeIP);
			portDao.updatePortIP(link.getRxPort_id(), toNodeIP);
			return true;
		}
		System.out.println("此场景下的链路名已经存在！");
		return false;
	}

	@RequestMapping("/deleteLink")
	@ResponseBody
	public String deleteLink(int s_id, String linkName) {
		// TODO Auto-generated method stub
		// 先删除底层云平台上的链路

		// 先根据s_id和linkName查找到对应的link
		LinkDao linkDao = new LinkDaoImpl();
		Link link = linkDao.getLink(s_id, linkName);
		// 拿到link后，调用deleteDao 删除link
		DeleteDao deleteDao = new DeleteDaoImpl();
		deleteDao.deleteLink(link);
		return "删除成功！";
	}

	public List<Link> getLinkList(int s_id) {
		// TODO Auto-generated method stub
		LinkDao dao = new LinkDaoImpl();
		return dao.getLinkList(s_id);
	}

}
