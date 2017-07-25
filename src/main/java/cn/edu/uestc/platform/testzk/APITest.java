package cn.edu.uestc.platform.testzk;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.Server;

import cn.edu.uestc.platform.winter.openstack.ServerUtils;

public class APITest {

	@Test
	public void demo2() {
		Server server = ServerUtils.getServer("docker3");
//		System.out.println("1");
		// Map<String, List<? extends Address>> map =
		// server.getAddresses().getAddresses();
//		System.out.println(server.getAddresses());
//		System.out.println(server.getAddresses().getAddresses());
		Map<String, List<? extends Address>> address = server.getAddresses().getAddresses();
		Collection<List<? extends Address>> addresses = address.values();
		for(List<? extends Address> list : addresses){
			for(Address addressss : list){
				System.out.println(addressss.getAddr());
			}
		}
		// System.out.println(map.keySet());
		// System.out.println("2");
		// Collection<List<? extends Address>> values = map.values();
		// System.out.println("3");
		// System.out.println(values);
		// isHaveIP(demo1(), "zk", ip);
	}
}
