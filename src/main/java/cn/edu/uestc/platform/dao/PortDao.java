package cn.edu.uestc.platform.dao;

import java.util.List;

import cn.edu.uestc.platform.pojo.Port;

public interface PortDao {
	
	public void insertPort(Port port);
	public List<Port> getPortList(int n_id);

}
