package cn.edu.uestc.platform.test.newTest;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.State;
import org.openstack4j.openstack.OSFactory;

import cn.edu.uestc.platform.utils.Constants;

public class CreatePortTest {

	public static void main(String[] args) {
		OSClientV3 os = OSFactory.builderV3()
                .endpoint(Constants.LOGIN_URL)
                .credentials("zph", "123456", Identifier.byName("default"))
                .scopeToProject(Identifier.byId("f766e0582674439fbd935cedfa404947"))//zph项目id
                .authenticate();
		Port port = os.networking().port().create(Builders.port()
	              .name("zphport")
	              .networkId("0d949832-c868-4d96-8b25-0b75771d425f")
	              .fixedIp("10.10.10.9", "b5519da3-f825-4bf8-9322-230d71450cbe").deviceId("173114ce-6c24-4ba3-b71d-40c1752394e6").deviceOwner("compute:nova").state(State.ACTIVE)
	              .build());
	
		Port updatedPort = os.networking().port().update(port.toBuilder().name("port-1-1").build());
	}
}
