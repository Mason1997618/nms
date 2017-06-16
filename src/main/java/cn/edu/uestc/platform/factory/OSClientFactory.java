package cn.edu.uestc.platform.factory;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.openstack.OSFactory;

import cn.edu.uestc.platform.utils.Constants;

public class OSClientFactory {

	/**
	 * 用户身份认证，线程独立，必须保证每个线程有一个单独的OSClientV2
	 * 
	 * @param username
	 *            Openstack登陆的用户名
	 * @param password
	 *            Openstack登陆的密码
	 * @param tenantName
	 *            该账户下的租户名称
	 * @return OSClientV3认证成功信息
	 */
	public static OSClientV3 authenticate(String username, String password, String projectId) {
		OSClientV3 os = OSFactory.builderV3()
                .endpoint(Constants.LOGIN_URL)
                .credentials(username, password, Identifier.byName("default"))
                .scopeToProject(Identifier.byId(projectId))//zph项目id
                .authenticate();
		return os;
	}

}
