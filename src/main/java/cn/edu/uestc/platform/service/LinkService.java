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
import cn.edu.uestc.platform.pojo.Port;

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
			LinkController controller = new LinkController();
			controller.createLinkMTM(fromNode.getNodeName(), fromNodeIP, toNode.getNodeName(), toNodeIP);

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

	/*
	 * 获得当前场景下的所有链路列表
	 */
	public List<Link> getLinkList(int s_id) {
		// TODO Auto-generated method stub
		LinkDao dao = new LinkDaoImpl();
		return dao.getLinkList(s_id);
	}

	/*
	 * 删除链路
	 */
	public boolean deleteLink(int s_id, String linkName) {

		// 先根据s_id和linkName查找到对应的link
		LinkDao linkDao = new LinkDaoImpl();
		Link link = linkDao.getLink(s_id, linkName);

		System.out.println("此链路为:" + link);
		// 根据link拿到link两端的端口IP，以及端口所属的节点。
		PortDao portDao = new PortDaoImpl();
		List<Port> ports = portDao.getPortByLink(link);
		System.out.println("端口号为:" + ports);

		// 根据portId拿到节点
		NodeDao nodeDao = new NodeDaoImpl();
		Node fromNode = nodeDao.getNodeByPortId(ports.get(0).getPt_id());
		Node toNode = nodeDao.getNodeByPortId(ports.get(1).getPt_id());

		// 删除底层云平台上的链路
		// 删除链路需要链路两端的节点名和两端的端口IP。
		LinkController controller = new LinkController();
		System.out.println(fromNode.getNodeName() + "---" + ports.get(0).getPortIp() + "-------" + toNode.getNodeName()
				+ "------" + ports.get(1).getPortIp());
		controller.delLinkMTM(fromNode.getNodeName(), ports.get(0).getPortIp(), toNode.getNodeName(),
				ports.get(1).getPortIp());
		// 拿到link后，调用deleteDao 删除link
		DeleteDao deleteDao = new DeleteDaoImpl();
		deleteDao.deleteLink(link);
		return true;
	}

	public void pauseLink(int s_id, String linkName) {
		// 删除openstack上的链路
		this.deleteLink(s_id, linkName);
		LinkDao dao = new LinkDaoImpl();
		dao.updateLinkStatustoDown(s_id, linkName);
	}

	public void recoveryLink(int s_id, String linkName) {
		
		LinkDao linkDao = new LinkDaoImpl();
		PortDao portDao = new PortDaoImpl();

		Link link = linkDao.getLink(s_id, linkName);
		List<Port> ports = portDao.getPortByLink(link);
		// 先创建openstack上的链路
		this.createLink(link, ports.get(0).getPortIp(), ports.get(1).getPortIp());
		//修改数据库中链路的状态
		linkDao.updateLinkStatusUp(s_id,linkName);
	}

}
