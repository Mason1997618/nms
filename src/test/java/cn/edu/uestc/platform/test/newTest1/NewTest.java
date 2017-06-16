package cn.edu.uestc.platform.test.newTest1;

import org.junit.Test;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;
import org.openstack4j.openstack.OSFactory;

public class NewTest {

	@Test
	public void fun1(){
		//身份认证通过
		OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://10.0.0.11:5000/v3")
                .credentials("admin", "123456", Identifier.byName("default"))
                .scopeToProject(Identifier.byId("6605817f15a04b38a25bce81cc8dab6f"))//admin项目id
                .authenticate();
		System.out.println(os.compute().flavors().list());
	}
	
	@Test
	public void fun2(){
		//完成可用空间域的操作
		OSClientV3 os = OSFactory.builderV3()
                .endpoint("http://10.0.0.11:5000/v3")
                .credentials("admin", "123456", Identifier.byName("default"))
                .scopeToProject(Identifier.byId("6605817f15a04b38a25bce81cc8dab6f"))//admin项目id
                .authenticate();
		
		//生成虚拟机实例，为什么要这样做呢，是因为添加了域空间
		ServerCreate sc = Builders.server().name("zph_test").flavor("1").image("e4498289-4a0b-4f64-8e80-8ec3525dfc42").availabilityZone("vm_zone")
				.build();
		// 添加固定外网端口的IP，用与外部通信ssh进入虚拟机
		sc.addNetwork("215a2e77-10bf-4948-928e-5d405af1ff30", "10.10.11.10");
		Server server = os.compute().servers().boot(sc);
		os.compute().servers().action(server.getId(), Action.START);
	}
	
}
