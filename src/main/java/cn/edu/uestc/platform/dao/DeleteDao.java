package cn.edu.uestc.platform.dao;

import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Port;

public interface DeleteDao {
	//删除链路时，链路两端对应的两个端口对应的ip地址清0，让端口的状态为0(未占用)
	public void deleteLink(Link link);
	
	//删除端口时，同时需要删除端口对应的链路，同时将链路的另一端的端口ip清0并让其状态为0，并且让节点的numberPort减1
	public void deletePortIncludeLink(Port port,int PortID1,int PortID2);
	
	//删除简单节点时，同时需要删除端口（当然删除端口的函数会调用删除链路的函数以及一系列的操作），并且从云平台删除实例,若有链路 删除链路
	
	public void deleteSimpleNode(Node node);

	
}
