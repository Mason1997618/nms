package cn.edu.uestc.platform.service;

import java.util.List;

import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.dao.PortDao;
import cn.edu.uestc.platform.dao.PortDaoImpl;
import cn.edu.uestc.platform.pojo.Port;

public class PortService {

	/*
	 * 新建端口,端口名一样是否允许？最大端口数是否限制？
	 */
	public boolean createPort(Port port) {
		// TODO Auto-generated method stub
		PortDao pdao = new PortDaoImpl();
		NodeDao ndao = new NodeDaoImpl();
		//port表插入port 
		pdao.insertPort(port);
		//节点表的portnumber字段+1
		ndao.plusNumberPort(port.getN_id());
		return true;
	}

	/*
	 * 返回端口列表
	 */
	
	public List<Port> getPortList(int n_id){
		PortDao dao = new PortDaoImpl();
		return dao.getPortList(n_id);
	}

	public List<Port> getPortListBynodeName(int s_id, String nodeName) {
		// TODO Auto-generated method stub
		PortDao pdao = new PortDaoImpl();
		return pdao.getPortListBynodeName(s_id,nodeName);
	}
	
	
}
