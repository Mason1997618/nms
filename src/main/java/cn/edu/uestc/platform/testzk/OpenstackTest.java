package cn.edu.uestc.platform.testzk;

import java.util.List;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.api.networking.NetFloatingIPService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.model.network.FloatingIP;
import org.openstack4j.model.network.NetFloatingIP;
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
				.credentials("zph", "123456", Identifier.byName("default"))
				.scopeToProject(Identifier.byId("7e0ba2f4b7e74f0eb21fec7642d42544")).authenticate();

		List<? extends Server> servers = os.compute().servers().list();
		// os.networking().
		NetFloatingIPService floatIP = os.networking().floatingip();
		List<? extends NetFloatingIP> floapIP = floatIP.list();
		System.out.println(floapIP);

		// List<? extends Subnet> subnets = os.networking().subnet().list();
		// String subnetID = null;
		// String ip = "192.168.6.33";
		// for (Subnet subnet : subnets) {
		// if (subnet.getCidr().substring(0, subnet.getCidr().lastIndexOf("."))
		// .equals(ip.substring(0, ip.lastIndexOf(".")))) {
		// subnetID = subnet.getNetworkId();
		// }
		// }
		// System.out.println(subnetID);
		// ServerCreate sc = Builders.server().name("Ubuntu
		// 3").flavor("2").image("cbeb615d-fae5-4df7-9fb6-5913fa4c63e9")
		// .build();
		// sc.addNetwork(subnetID, ip);
		// Server server = os.compute().servers().boot(sc);
		// os.compute().servers().action(server.getId(), Action.START);

	}

	public OSClientV3 authenticate() {
		OSClientV3 os = OSFactory.builderV3().endpoint("http://10.0.0.11:5000/v3")
				.credentials("zk", "123456", Identifier.byName("default"))
				.scopeToProject(Identifier.byId("8b21d3e746af43a9af7152d72ad54d02")).authenticate();
		return os;
	}

	/*
	 * 创建节点
	 */
	@Test
	public void createNode() {
		OSClientV3 os = this.authenticate();
		List<? extends Subnet> subnets = os.networking().subnet().list();
		String subnetID = null;
		String ip = "192.168.7.39";
		for (Subnet subnet : subnets) {
			if (subnet.getCidr().substring(0, subnet.getCidr().lastIndexOf("."))
					.equals(ip.substring(0, ip.lastIndexOf(".")))) {
				subnetID = subnet.getNetworkId();
			}
		}
		System.out.println(subnetID);
		ServerCreate sc = Builders.server().name("Ubuntu 4").flavor("2").image("cbeb615d-fae5-4df7-9fb6-5913fa4c63e9")
				.build();
		sc.addNetwork(subnetID, ip);
		Server server = os.compute().servers().boot(sc);
		System.out.println(server.getId());
		ActionResponse response = os.compute().servers().action(server.getId(), Action.START);
		System.out.println(response);
		System.out.println(response.isSuccess());

	}

	/*
	 * 删除节点
	 */
	@Test
	public void deleteNode(/* int serverId */) {
		OSClientV3 os = this.authenticate();
		ActionResponse response = os.compute().servers().delete("7b0576d8-82b5-4fe1-a1f3-173914cd0434");
		System.out.println(response.isSuccess());
	}

	/*
	 * 删除一个实例 必须通过实例的ID来删除，但是如何获得ID呢？可以通过查名字来获得实例的ID,但是实例有可能是有重复的
	 * 那么，是否可以通过节点的其他信息来获取实例的ID号？
	 */
	@Test
	public void deleteNodeFromNodeName() {
		OSClientV3 os = this.authenticate();
		List<? extends Server> servers = os.compute().servers().list();
		for (Server server : servers) {
			if (server.getName().equals("Ubuntu 4")) {
				ActionResponse response = os.compute().servers().delete(server.getId());
				System.out.println(response);
				System.out.println(response.isSuccess());
				System.out.println(server.getName());
			}
		}

	}

}

// /*
// * 分配浮动ip
// */
// @Test
// public void demo2(){
// OSClientV3 os =
// OSFactory.builderV3().endpoint("http://10.0.0.11:5000/v3")
// .credentials("zk", "123456", Identifier.byName("default"))
// .scopeToProject(Identifier.byId("8b21d3e746af43a9af7152d72ad54d02")).authenticate();
// System.err.println(os);
// List<FloatingIP> ips = (List<FloatingIP>)
// os.compute().floatingIps().list();
// System.out.println(ips);
// }