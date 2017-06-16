package cn.edu.uestc.platform.dynamicChange;

import java.awt.TextArea;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.openstack4j.api.OSClient.OSClientV3;

import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.factory.ServerFactory;
import cn.edu.uestc.platform.utils.Constants;

public class DynamicController {

	/**
	 * 1.生成所有的卫星节点
	 */
	public void createAllNodes() {
		// 1.拿到所有的预先生成卫星节点
		Set<String> nodes = new HashSet<>();
		for (String[] name : IPCreateFactory.createTableUD()) {// LEO
			nodes.add(name[0]);
			nodes.add(name[1]);
		}
		for (String[] name : IPCreateFactory.createTableGEO()) {// GEO
			nodes.add(name[0]);
			nodes.add(name[1]);
		}
		for (String[] name : IPCreateFactory.createTableGEOToGround()) {// GEO
			nodes.add(name[0]);
			nodes.add(name[1]);
		}
		nodes.add("Aircraft1");
		System.out.println(nodes.size() + " " + nodes);
		// 2.生成所有的卫星节点
		int i = 10;
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		for (String node : nodes) {
			ServerFactory.createServer(os, node, "192.168.5." + i++);
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 2.生成所有的网段
	 */
	public void createAllLinks() {
		// 2.拿到所有的预先生成的网段
		Set<String> bridges = new HashSet<>();
		for (String[] bridge : IPCreateFactory.createTableUD()) {
			bridges.add(bridge[2]);
		}
		for (String[] bridge : IPCreateFactory.createTableLR()) {
			bridges.add(bridge[2]);
		}
		for (String[] bridge : IPCreateFactory.createTableGEO()) {
			bridges.add(bridge[2]);
		}
		//手动添加 GEO TO GROUND
		bridges.add("10.40.1.4");
		//手动添加 LEO TO GEO
		bridges.add("10.50.1.4");
		bridges.add("10.50.2.4");
		bridges.add("10.50.3.4");
		//手动添加Aircraft1 对LEO
		bridges.add("10.60.1.4");
		System.out.println(bridges.size() + " " + bridges);

		for (String bridge : bridges) {
			DynamicNetWorkUtils.createSubnetByIP(bridge);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 生成t0时刻的拓扑表
	 */
	public void t0() throws Exception {
		// 1.LEO左右的卫星
		TreeSet<NewLink> linksLR = Filters.readMinuteMEORL(0 + "");// 拿到当前分钟的链路
		System.out.println(linksLR);
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		for (NewLink link : linksLR) {
			DynamicFactory.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[0],
					IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[2]);
			TimeUnit.SECONDS.sleep(2);
			DynamicFactory.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[1],
					IPCreateFactory.searchData(link, IPCreateFactory.createTableLR())[3]);
			TimeUnit.SECONDS.sleep(2);
		}
		
		// 2.LEO上下的卫星
		for (String[] link : IPCreateFactory.createTableUD()) {
			DynamicFactory.addPort(os, link[0], link[2]);
			TimeUnit.SECONDS.sleep(2);
			DynamicFactory.addPort(os, link[1], link[3]);
			TimeUnit.SECONDS.sleep(2);
		}
		// 3.GEO之间的链接
		for (String[] link : IPCreateFactory.createTableGEO()) {
			DynamicFactory.addPort(os, link[0], link[2]);
			TimeUnit.SECONDS.sleep(2);
			DynamicFactory.addPort(os, link[1], link[3]);
			TimeUnit.SECONDS.sleep(2);
		}
		// 4.GEO对地面节点的链接
		for (String[] link : IPCreateFactory.createTableGEOToGround()) {
			DynamicFactory.addPort(os, link[0], link[2]);
			TimeUnit.SECONDS.sleep(2);
			DynamicFactory.addPort(os, link[1], link[3]);
			TimeUnit.SECONDS.sleep(2);
		}
		// 6.Aircraft1对 LEO的链接
		for (String[] link : IPCreateFactory.createTablePToLEO()) {
			DynamicFactory.addPort(os, link[0], link[2]);
			TimeUnit.SECONDS.sleep(2);
			DynamicFactory.addPort(os, link[1], link[3]);
			TimeUnit.SECONDS.sleep(2);
		}
		os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		// 5.GEO对LEO之间的链接
		TreeSet<NewLink> linksGEOToMeo = Filters.readMinuteMEOToGEO("0");
		System.out.println(linksGEOToMeo);
		
		for (NewLink link : linksGEOToMeo) {
			//应该有一张GEO TO LEO的表
			DynamicFactory.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[0],
					IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[2]);
			TimeUnit.SECONDS.sleep(2);
			DynamicFactory.addPort(os, IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[1],
					IPCreateFactory.searchData(link, IPCreateFactory.createTableGEOToMEO())[3]);
			TimeUnit.SECONDS.sleep(2);
		}
	}

	/**
	 * 动态状态：延迟一分钟之后开始每分钟动态的改变
	 */
	public void change(TextArea text) {
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(10);
		DynamicTask task = new DynamicTask(text);
		exec.scheduleAtFixedRate(task, 1, 1, TimeUnit.MINUTES);
	}

	public static void main(String[] args) throws Exception {
		DynamicController c = new DynamicController();
//		 c.createAllNodes();
//		 c.createAllLinks();
//		 c.t0();
		 c.change(null);
	}
}
