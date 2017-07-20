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
	public boolean createLink(Link link) {

		LinkDao linkDao = new LinkDaoImpl();
		// 当使用||时，||前面的条件如果已经为真的话，之后的条件则不会进行判断,所以不用担心之后的link会空指针异常
		if (linkDao.ishaveLinkName(link) == false || link.getLinkStatus() == 1) {
			// 在云平台创建一条链路,通过port来获取到fronNodeName和toNodeName
			NodeDao nodeDao = new NodeDaoImpl();
			Node fromNode = nodeDao.getNodeByPortId(link.getTxPort_id());
			Node toNode = nodeDao.getNodeByPortId(link.getRxPort_id());
			LinkController controller = new LinkController();
			controller.createLinkMTM(link.getFromNodeName(), link.getFromNodeIP(), link.getToNodeName(),
					link.getToNodeIP());

			// 新建的链路linkStatus的状态都是0， 可插入链路，如果链路已经存在，数据库中无需再插入链路了。
			if (link.getLinkStatus() == 0) {
				linkDao.insertLink(link);
				// 更新port表的ip地址,以及链路状态
				PortDao portDao = new PortDaoImpl();
				portDao.updatePortIP(link.getTxPort_id(), link.getFromNodeIP());
				portDao.updatePortIP(link.getRxPort_id(), link.getToNodeIP());
			}
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
	 * 删除链路，删除链路函数用于删除链路状态为0的链路，挂起链路 状态为0的链路
	 * 一切删除，状态为1的链路调用此函数仅用于删除Openstack上的链路而不操作数据库。
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
		// 拿到link后，调用deleteDao 删除link,只有非挂起状态的link才能删除数据库
		// 删除底层云平台上的链路
		// 删除链路需要链路两端的节点名和两端的端口IP。
		LinkController controller = new LinkController();
		controller.delLinkMTM(fromNode.getNodeName(), ports.get(0).getPortIp(), toNode.getNodeName(),
				ports.get(1).getPortIp());
		if (link.getLinkStatus() == 0) {
			DeleteDao deleteDao = new DeleteDaoImpl();
			deleteDao.deleteLink(link);
		}
		return true;
	}

	public void pauseLink(int s_id, String linkName) {
		// 将link设置为挂起态
		LinkDao dao = new LinkDaoImpl();
		dao.updateLinkStatustoDown(s_id, linkName);
		// 删除openstack上的链路
		this.deleteLink(s_id, linkName);
		System.out.println("挂起链路成功！");
	}

	/*
	 * 恢复链路
	 */
	public void recoveryLink(int s_id, String linkName) {
		LinkDao linkDao = new LinkDaoImpl();
		PortDao portDao = new PortDaoImpl();
		Link link = linkDao.getLink(s_id, linkName);
		// 先创建openstack上的链路
		this.createLink(link);
		// 修改数据库中链路的状态
		linkDao.updateLinkStatusUp(s_id, linkName);
	}

}
