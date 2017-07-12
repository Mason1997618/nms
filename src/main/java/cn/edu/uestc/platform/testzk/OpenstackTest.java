package cn.edu.uestc.platform.testzk;

import java.util.List;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.network.FloatingIP;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.OSFactory;


public class OpenstackTest {

	/*
	 * 启动一个实例并且把他挂在对应的网段上
	 */
	@Test
	public void demo1() {
		// OSClientV3 os = OSFactory.builderV3()
		// .endpoint("http://10.0.0.11:5000/v3")
		// .credentials("zk", "123456", Identifier.byName("default"))
		// .scopeToProject(Identifier.byId("8b21d3e746af43a9af7152d72ad54d02"))
		// .authenticate();
		OSClientV3 os = OSFactory.builderV3().endpoint("http://10.0.0.11:5000/v3")
				.credentials("zk", "123456", Identifier.byName("default"))
				.scopeToProject(Identifier.byId("8b21d3e746af43a9af7152d72ad54d02")).authenticate();
		
		List<? extends Subnet> subnets = os.networking().subnet().list();
		String subnetID = null;
		String ip = "192.168.6.33";
		for (Subnet subnet : subnets) {
			if (subnet.getCidr().substring(0, subnet.getCidr().lastIndexOf("."))
					.equals(ip.substring(0, ip.lastIndexOf(".")))) {
				subnetID = subnet.getNetworkId();
			}
		}
		System.out.println(subnetID);
		ServerCreate sc = Builders.server().name("Ubuntu 3").flavor("2").image("cbeb615d-fae5-4df7-9fb6-5913fa4c63e9")
				.build();
		sc.addNetwork(subnetID, ip);
		Server server = os.compute().servers().boot(sc);
		os.compute().servers().action(server.getId(), Action.START);

	}
	
	/*
	 * 分配浮动ip
	 */
	@Test
	public void demo2(){
		OSClientV3 os = OSFactory.builderV3().endpoint("http://10.0.0.11:5000/v3")
				.credentials("zk", "123456", Identifier.byName("default"))
				.scopeToProject(Identifier.byId("8b21d3e746af43a9af7152d72ad54d02")).authenticate();
		System.err.println(os);
		List<FloatingIP> ips = (List<FloatingIP>) os.compute().floatingIps().list();
		System.out.println(ips);
	}

}
