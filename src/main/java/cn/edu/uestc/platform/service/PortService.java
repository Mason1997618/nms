package cn.edu.uestc.platform.service;

import java.util.List;
import cn.edu.uestc.platform.controller.LinkController;
import cn.edu.uestc.platform.dao.DeleteDao;
import cn.edu.uestc.platform.dao.DeleteDaoImpl;
import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.dao.PortDao;
import cn.edu.uestc.platform.dao.PortDaoImpl;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Port;

public class PortService {

	/*
	 * 新建端口,端口名一样是否允许？最大端口数是否限制？
	 */
	public boolean createPort(Port port) {
		// TODO Auto-generated method stub
		PortDao pdao = new PortDaoImpl();
		NodeDao ndao = new NodeDaoImpl();
		// port表插入port
		pdao.insertPort(port);
		// 节点表的portnumber字段+1
		ndao.plusNumberPort(port.getN_id());
		return true;
	}

	/*
	 * 返回端口列表
	 */
	public List<Port> getPortList(int n_id) {
		PortDao dao = new PortDaoImpl();
		return dao.getPortList(n_id);
	}

	/*
	 * 通过节点名称获取对应的所有端口列表
	 */
	public List<Port> getPortListBynodeName(int s_id, String nodeName) {
		PortDao pdao = new PortDaoImpl();
		return pdao.getPortListBynodeName(s_id, nodeName);
	}

	/*
	 * 通过端口id删除对应的端口,同时删除对应的openstack上的链路。
	 */
	public void deletePort(int pt_id) {

		PortDao portDao = new PortDaoImpl();
		NodeDao nodeDao = new NodeDaoImpl();
		DeleteDao deteledao = new DeleteDaoImpl();
		// 拿到被删除的port
		Port port = portDao.getPortByID(pt_id);
		// 拿到删除port另一端的port
		int portID[] = portDao.getPortIdsOnSameLinkByPort(port);
		List<Port> ports = portDao.getportListByPortIDs(portID);
		Node fromNode = nodeDao.getNodeByPortId(portID[0]);
		Node toNode = nodeDao.getNodeByPortId(portID[1]);
		// 先删除端口所对应的链路
		LinkController controller = new LinkController();
		controller.delLinkMTM(fromNode.getNodeName(), ports.get(0).getPortIp(), toNode.getNodeName(),
				ports.get(1).getPortIp());

		// 再删除端口
		deteledao.deletePort(port, portID[0], portID[1]);
	}

}
