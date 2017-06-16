package cn.edu.uestc.platform.test.newTest;

import java.util.List;
import java.util.Set;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.network.IP;
import org.openstack4j.model.network.Network;
import org.openstack4j.model.network.Port;
import org.openstack4j.model.network.Subnet;

import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.utils.Constants;

public class DelSubNet {

	public static void main(String[] args) {
		String delIP = "10.10.10.3";
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		//1.先删除端口
		List<? extends Port> ports = os.networking().port().list();
		for(Port port :ports){
			 Set<? extends IP> ips = port.getFixedIps();
			 for(IP ip:ips){
				 if(ip.getIpAddress().contains(delIP.substring(0, delIP.lastIndexOf(".")))){
					 os.networking().port().delete(port.getId());
				 }
			 }
		}
		//2.删除子网
		List<? extends Subnet> subnets = os.networking().subnet().list();
		for(Subnet subnet:subnets){
			if(subnet.getName().equals("link"+delIP.substring(0, delIP.lastIndexOf("."))+".0")){
				os.networking().subnet().delete(subnet.getId());
			}
		}
		//3.删除网段
		List<? extends Network> networks = os.networking().network().list();
		for(Network network:networks){
			if(network.getName().equals("link"+delIP.substring(0, delIP.lastIndexOf("."))+".0")){
				os.networking().network().delete(network.getId());
			}
		}
	}
}
