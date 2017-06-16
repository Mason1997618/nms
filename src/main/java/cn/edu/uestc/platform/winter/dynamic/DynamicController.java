package cn.edu.uestc.platform.winter.dynamic;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openstack4j.api.OSClient.OSClientV3;

import cn.edu.uestc.platform.dynamicChange.DynamicNetWorkUtils;
import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.factory.ServerFactory;
import cn.edu.uestc.platform.utils.Constants;
import cn.edu.uestc.platform.winter.pojo.STKLink;
import cn.edu.uestc.platform.winter.stkAnalyze.IPDynamicCreateFactory;
import cn.edu.uestc.platform.winter.stkAnalyze.STKFilters;
import cn.edu.uestc.platform.winter.stkAnalyze.SateliteNumFilter;

public class DynamicController {

	// 1.生成所有的卫星节点
	public void createAllNodes() {
		Set<String> nodes = STKFilters.getAllNodes("abc.csv");
		System.out.println(nodes.size() + " " + nodes);
		// 开始生成节点
		int i = 10;// 规划的IP地址
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		for (String node : nodes) {
			ServerFactory.createServer(os, node, "192.168.5." + i++);
			try {
				TimeUnit.SECONDS.sleep(2);// 每产生一个虚拟机休眠 2s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 2.预生成所有的卫星链路 网段
	public void createAllLinks() {
		Set<String> bridges = IPDynamicCreateFactory.getAllLinks();
		System.out.println(bridges.size() + " " + bridges);
		for (String bridge : bridges) {
			DynamicNetWorkUtils.createSubnetByIP(bridge);
			try {
				TimeUnit.SECONDS.sleep(1);//每建立一个网段休眠 1s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 3.生成 t0 时刻的拓扑表
	public void createT0Topology() {
		// 1.静态拓扑
		// 2.动态拓扑
		ArrayList<STKLink> data = STKFilters.getMinutesData("0");
		// System.out.println(data);
	}

	public static void main(String[] args) {
		new DynamicController().createAllLinks();
	}
}
