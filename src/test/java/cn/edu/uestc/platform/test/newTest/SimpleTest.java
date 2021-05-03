package cn.edu.uestc.platform.test.newTest;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.identity.v3.Project;
import org.openstack4j.model.network.IPVersionType;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Subnet;

import cn.edu.uestc.platform.dynamicChange.DynamicNetWorkUtils;
import cn.edu.uestc.platform.dynamicChange.Filters;
import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.factory.ServerFactory;
import cn.edu.uestc.platform.utils.Constants;

public class SimpleTest {
	
	@Test
	public void fun111(){
		System.out.println(Filters.readMinuteMEORL("7"));
		System.out.println(Filters.readMinuteMEORL("6"));
		System.out.println(Filters.readMinuteMEOToGEO("5"));
		System.out.println(Filters.readMinuteMEOToGEO("6"));
	}

	@Test
	public void fun0() {
		String id = NetworkUtils.getIdByIp("192.168.5.9");
		System.out.println(id);
	}

	@Test
	public void fun() {
		System.out.println(NetworkUtils.getNameByIp("10.10.10.3"));
	}

	@Test
	public void fun1() {
//		new NodeController().createNode("VM1", "192.168.5.5");
	}

	@Test
	public void fun2() {
		System.out.println(NetworkUtils.isHaveIP(
				OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID), "VM1", "10.10.10.2"));
	}

	@Test
	public void fun3() {
		System.out.println(NetworkUtils.getNameByIp("192.168.5.10"));
	}

	@Test
	public void fun4() {
		System.out.println(NetworkUtils.getFloatIpByNodeName("VM1"));
	}

	@Test
	public void fun5() {
		ServerFactory.addPort(OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID), "VM2",
				"10.10.10.3");
	}

	public void fun7() {
		OSClientV3 os = OSClientFactory.authenticate("admin", "123456", Constants.ADMIN_PROJECT_ID);
		Project project = os.identity().projects().get("f766e0582674439fbd935cedfa404947");
		String ip = "10.20.10.3";
		Network network = os.networking().network().create(Builders.network()
				.name("link" + ip.substring(0, ip.lastIndexOf(".")) + ".0").tenantId(project.getId()).build());// 网络的名称为link+ip
		Subnet subnet = os.networking().subnet()// 无DHCP
				.create(Builders.subnet().name("link" + ip.substring(0, ip.lastIndexOf(".")) + ".0")
						.networkId(network.getId()).tenantId(project.getId()).ipVersion(IPVersionType.V4)
						.cidr(ip.substring(0, ip.lastIndexOf(".")) + ".0/24").build());
	}

	public void fun8() {
		// 测试DHCP增删端口

	}

	public void fun9() {
		NetworkUtils.addIPToNode("VM1", "10.10.10.4");
	}

	public static void main(String[] args) {
		// NetworkUtils.addIPToNode(OSClientFactory.authenticate("zph",
		// "123456", Constants.ZPH_PROJECT_ID), "VM1",
		// "10.20.22.6");
//		DynamicNetWorkUtils.changeAddQuguaConf("192.168.1.129", "192.168.122.38");
		// DynamicNetWorkUtils.createSubnetByIP("10.20.22.2");
		// NetworkUtils.addIPToNode(OSClientFactory.authenticate("zph",
		// "123456", Constants.ZPH_PROJECT_ID), "VM1",
		// "10.20.22.4");
		DynamicNetWorkUtils.changeDelQuguaConf("192.168.1.129", "192.168.122.38");
	}

}
