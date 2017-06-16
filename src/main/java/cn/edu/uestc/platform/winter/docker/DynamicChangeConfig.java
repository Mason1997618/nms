package cn.edu.uestc.platform.winter.docker;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cn.edu.uestc.platform.utils.Constants;
import cn.edu.uestc.platform.utils.SSHExecutorUtils;

/**
 * 启动docker实例的quagga程序
 */
public class DynamicChangeConfig {

	/**
	 * docker 添加路由软件的配置信息，需要读取ifconfig文件中网卡的配置信息
	 */
	public static void changeAddQuguaConf(String nodeName, String addIp) {
		ArrayList<String> list = new ArrayList<>();// 所有IP地址按照顺序排列
		SSHExecutorUtils ssh = new SSHExecutorUtils(Constants.DOCKER_NODE_USERNAME, "123456",
				Constants.DOCKER_NODE_IP_ADDRESS);
		String match = "";
		String first = "";
		String eth = "";// 网卡名称
		try {
			while (list.indexOf(addIp) < 0) {
				list.clear();
				TimeUnit.SECONDS.sleep(1);
				match = ssh.exec("sudo docker exec " + nodeName + " ifconfig");
				first = match.substring(match.indexOf("inet addr:") + 10, match.length());
				while (first.indexOf("inet addr:") > 0) {
					if (first.indexOf("  Bcast:") > 0) {
						String ip = first.substring(first.indexOf("inet addr:") + 10, first.indexOf("  Bcast:"));
						list.add(ip);
						if (ip.equals(addIp)) {
							eth = first.substring(first.indexOf("\nns") + 1, first.indexOf(" Link encap:Ethernet"));
						}
						first = first.substring(first.indexOf("  Bcast:") + 8, first.length());
					} else {
						first = first.substring(first.indexOf("inet addr:") + 10, first.length());
					}
				}
			}
			System.out.println("新增加的网卡为 " + eth+" "+addIp);
			//成功拿到网卡的名称,执行zebra脚本
			ssh.exec("sudo docker exec -d "+nodeName+ " ./rip_add.sh " + eth+" "+ addIp +"/24 "+addIp.substring(0, addIp.lastIndexOf(".")) + ".0/24");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ssh.close();
		}
	}

	/**
	 * docker 删除路由软件的配置信息，需要读取ifconfig文件中网卡的配置信息
	 */
	public static void changeDelQuguaConf(String nodeName, String delIp) {
		ArrayList<String> list = new ArrayList<>();// 所有IP地址按照顺序排列
		SSHExecutorUtils ssh = new SSHExecutorUtils(Constants.DOCKER_NODE_USERNAME, "123456",
				Constants.DOCKER_NODE_IP_ADDRESS);
		String match = "";
		String first = "";
		String eth = "";// 网卡名称
		try {
			while (list.indexOf(delIp) < 0) {
				list.clear();
				TimeUnit.SECONDS.sleep(1);
				match = ssh.exec("sudo docker exec " + nodeName + " ifconfig");
				first = match.substring(match.indexOf("inet addr:") + 10, match.length());
				while (first.indexOf("inet addr:") > 0) {
					if (first.indexOf("  Bcast:") > 0) {
						String ip = first.substring(first.indexOf("inet addr:") + 10, first.indexOf("  Bcast:"));
						list.add(ip);
						if (ip.equals(delIp)) {
							eth = first.substring(first.indexOf("\nns") + 1, first.indexOf(" Link encap:Ethernet"));
						}
						first = first.substring(first.indexOf("  Bcast:") + 8, first.length());
					} else {
						first = first.substring(first.indexOf("inet addr:") + 10, first.length());
					}
				}
			}
			System.out.println("要删除的网卡为 " + eth+" "+delIp);
			//成功拿到网卡的名称,执行zebra脚本
			ssh.exec("sudo docker exec -d "+nodeName+ " ./rip_del.sh " + eth+" "+ delIp +"/24 "+delIp.substring(0, delIp.lastIndexOf(".")) + ".0/24");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ssh.close();
		}
	}
	
	
	
	public static void main(String[] args) {
		changeAddQuguaConf("nova-add4223b-3419-46a0-8482-6cdb003c6fc4", "10.10.20.3");
	}
}
