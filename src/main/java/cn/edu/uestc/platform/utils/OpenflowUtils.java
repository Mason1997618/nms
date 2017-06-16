package cn.edu.uestc.platform.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 流表操作工具类
 */
public class OpenflowUtils {
	private static Logger logger = Logger.getLogger(OpenflowUtils.class);

	public static void addFlow(String fromNode, String fromNodeIP, String toNode, String toNodeIP, String linkNodeIP) {
		// 找到三台机器的mac地址
		String fromNodeMac = NetworkUtils.findMac(fromNode, fromNodeIP);
		String toNodeMac = NetworkUtils.findMac(toNode, toNodeIP);
		String linkNodeMac = NetworkUtils.findMac("link#" + linkNodeIP, linkNodeIP);
		logger.info("三台机器的mac地址为：" + fromNodeMac + " " + toNodeMac + " " + linkNodeMac);

		// 找到三台机器所属的nova节点
		String fromNodeNovaName = NetworkUtils.findNoveName(NetworkUtils.findServerID(fromNode, fromNodeIP));
		String toNodeNovaName = NetworkUtils.findNoveName(NetworkUtils.findServerID(toNode, toNodeIP));
		String linkNodeNovaName = NetworkUtils
				.findNoveName(NetworkUtils.findServerID("link#" + linkNodeIP, linkNodeIP));
		logger.info("三台机器的nova节点为：" + fromNodeNovaName + " " + toNodeNovaName + " " + linkNodeNovaName);
		// 找到三台机器的端口号
		String fromNodePort = findPort(fromNodeNovaName, fromNodeMac);
		String toNodePort = findPort(toNodeNovaName, toNodeMac);
		String linkNodePort = findPort(linkNodeNovaName, linkNodeMac);
		logger.info("三台机器的端口号为：" + fromNodePort + " " + toNodePort + " " + linkNodePort);
		// 开始下发流表
		xiafaliubiao(fromNodeNovaName, fromNodeMac, fromNodePort, linkNodeMac, linkNodePort, toNodeMac);
		xiafaliubiao(toNodeNovaName, toNodeMac, toNodePort, linkNodeMac, linkNodePort, fromNodeMac);
	}

	// 下发流表
	private static void xiafaliubiao(String AName, String AMac, String APort, String BMac, String BPort, String CMac) {// B是中间节点
		String fromnodeFloatingIp = "10.0.0.31";
		if (AName.contains("nova2")) {
			fromnodeFloatingIp = "10.0.0.32";
		}
		SSHExecutorUtils ssh = new SSHExecutorUtils("nova1", "123456", fromnodeFloatingIp);
		try {
			ssh.exec("sudo ovs-ofctl -O openflow13 add-flow br-int table=100,in_port=" + APort
					+ ",dl_type=0x0800,dl_src=" + AMac + ",dl_dst=" + CMac + ",actions=mod_dl_dst:" + BMac
					+ ",goto_table:110");
		} catch (Exception e) {

		}
		ssh.close();
	}

	// 根据novaName和mac地址找到mac地址对应的端口号
	public static String findPort(String novaName, String mac) {
		String nodeFloatingIp = "10.0.0.31";
		if (novaName.contains("nova2")) {
			nodeFloatingIp = "10.0.0.32";
		}
		if (mac.substring(0, 2).equals("fa")) {
			mac = "fe" + mac.substring(2, mac.length());// 替代首个fa
		}
		// System.out.println(mac);
		SSHExecutorUtils ssh = new SSHExecutorUtils("nova1", "123456", nodeFloatingIp);
		try {
			String message = ssh.exec("sudo ovs-ofctl -O openflow13 show br-int\n");	
			Pattern p = Pattern.compile("\\s(\\d+).*" + mac);
			Matcher m = p.matcher(message);
			while (m.find()) {
				ssh.close();
				return m.group(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
