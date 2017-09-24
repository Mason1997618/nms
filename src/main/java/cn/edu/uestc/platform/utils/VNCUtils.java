package cn.edu.uestc.platform.utils;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.openstack.compute.internal.ServerServiceImpl;

import cn.edu.uestc.platform.factory.OSClientFactory;

public class VNCUtils {

	public static String getVNCURL(String SeverID) {
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		return os.compute().servers().getVNCConsole(SeverID, null).getURL();
	}
}
