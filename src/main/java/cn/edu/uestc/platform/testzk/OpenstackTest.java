package cn.edu.uestc.platform.testzk;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Address;
import org.openstack4j.model.compute.FloatingIP;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.model.identity.v3.Token;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.NetFloatingIP;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;
import org.openstack4j.openstack.OSFactory;
import org.openstack4j.openstack.compute.domain.NovaVNCConsole;
import org.openstack4j.openstack.compute.internal.ServerServiceImpl;
import org.openstack4j.openstack.tacker.domain.TackerVnfd.VnfdConcreteBuilder;

import cn.edu.uestc.platform.winter.openstack.ServerUtils;

public class OpenstackTest {

	@Test
	public void demo2() {
		Server server = ServerUtils.getServer("zk1");
		Map<String, List<? extends Address>> map = server.getAddresses().getAddresses();
		Collection<List<? extends Address>> values = map.values();
		System.out.println(values);
		// isHaveIP(demo1(), "zk", ip);
	}

	/*
	 * 启动一个实例并且把他挂在对应的网段上
	 */
	@Test
	public void demo1() {

		OSClientV3 os  = authenticate();
		Token token = os.identity().tokens().get("457935b9-a1e7-436b-a514-24a054818119");
		System.out.println(token);
		NovaVNCConsole c = new NovaVNCConsole();
		ServerServiceImpl serverservice = new ServerServiceImpl();
		serverservice.getVNCConsole("", VNCConsole.Type.NOVNC).getURL();
		
	}

	public OSClientV3 authenticate() {
		OSClientV3 os = OSFactory.builderV3().endpoint("http://10.0.0.11:5000/v3")
				.credentials("zk", "123456", Identifier.byName("default"))
				.scopeToProject(Identifier.byId("8b21d3e746af43a9af7152d72ad54d02")).authenticate();
		return os;
	}
	
	
}