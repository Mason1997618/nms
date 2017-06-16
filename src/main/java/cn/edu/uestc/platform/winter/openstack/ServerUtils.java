package cn.edu.uestc.platform.winter.openstack;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Server;

import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.utils.Constants;

/**
 * OpenStack实例工具类
 * @author User
 *
 */
public class ServerUtils {
	
	/**
	 * 根据实例的名称 拿到一个实例 对象Server
	 */
	public static Server getServer(String serverName){
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		List<? extends Server> servers = os.compute().servers().list();
		for(Server server:servers){
			if(server.getName().equals(serverName)){
				return server;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(getServer("1"));
	}
}
