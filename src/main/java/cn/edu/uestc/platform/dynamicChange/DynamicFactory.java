package cn.edu.uestc.platform.dynamicChange;

import org.openstack4j.api.OSClient.OSClientV3;

import cn.edu.uestc.platform.utils.NetworkUtils;

public class DynamicFactory {
	/**
	 * 为一个节点动态添加端口,同时进入节点修改配置文件(zebra的配置文件)
	 */
	public static void addPort(OSClientV3 os, String nodeName, String portIP) {
		// 如果名为nodeName的虚拟机有 portIP这个ip地址，则不对它进行任何操作
		if (!NetworkUtils.isHaveIP(os, nodeName, portIP)) {
			// 将这个ip添加到节点上
			NetworkUtils.addIPToNode(nodeName, portIP);
			// 进入节点修改配置文件
			String floatIP = NetworkUtils.getFloatIpByNodeName(nodeName);// 通过虚拟机的名称得到浮动ip的地址
			System.out.println("虚拟机 " + nodeName + " 的浮动ip地址为： " + floatIP);
			// NetworkUtils.changeNetworkConfig(floatIP, portIP);
			// 通过浮动ip进入虚拟机修改配置文件
			DynamicNetWorkUtils.changeAddQuguaConf(floatIP, portIP);
		}
	}

	/**
	 * 为一个节点动态删除端口，同时进入节点修改配置文件(zebra)=====>TODO
	 */
	public static void delPort(OSClientV3 os, String nodeName, String portIP) {
		// 如果名为nodeName的虚拟机有 portIP这个ip地址，则对它进行删除操作
		if (NetworkUtils.isHaveIP(os, nodeName, portIP)) {
			System.out.println("准备删除了！");
			// 进入节点修改配置文件
			String floatIP = NetworkUtils.getFloatIpByNodeName(nodeName);//通过虚拟机的名称得到浮动ip的地址
			System.out.println("虚拟机 " + nodeName + " 的浮动ip地址为： " + floatIP);
			// 通过浮动ip进入虚拟机修改配置文件
			DynamicNetWorkUtils.changeDelQuguaConf(floatIP, portIP);
			// 将这个节点 删除这个portIP
			DynamicNetWorkUtils.delIPToNode(nodeName, portIP);//这块还没有测试------------**************************一定要先测试了这个方法！！！！！
		}
	}
}
