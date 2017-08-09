package cn.edu.uestc.platform.utils;

import org.openstack4j.model.compute.VNCConsole;
import org.openstack4j.openstack.compute.internal.ServerServiceImpl;

public class VNCUtils {

	public static String getVNCURL(String SeverID) {
		ServerServiceImpl serverservice = new ServerServiceImpl();
		return serverservice.getVNCConsole(SeverID, VNCConsole.Type.NOVNC).getURL();
	}
}
