package cn.edu.uestc.platform.testzk;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import org.junit.Test;

import cn.edu.uestc.platform.dao.UserDaoImpl;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.winter.pojo.STKLink;
import cn.edu.uestc.platform.winter.stkAnalyze.STKRangeDataAnalyze;

public class TestDemo {

	@Test
	public void TestDemo1() {
		LinkedHashSet<STKLink> allData = STKRangeDataAnalyze.getAllLinks("abc.csv");
		for (STKLink alldata : allData) {
			System.out.println(alldata);
		}
	}
	
	
	@Test
	public static ArrayList<STKLink> getMinutesData(String minute) {
		TreeSet<STKLink> minuteLEOToLEOLinks = new TreeSet<>(new DynamicComparator());
		TreeSet<STKLink> minuteLEOToGEOLinks = new TreeSet<>(new DynamicComparator());
		TreeSet<STKLink> minuteLEOToAirLinks = new TreeSet<>(new StaticComparator());
		TreeSet<STKLink> minuteLEOToGroLinks = new TreeSet<>(new StaticComparator());
		for (STKLink link : STKRangeDataAnalyze.getAllLinks("abc.csv")) {  // 拿到当前的时间最完整的所有链路信息
			if (link.getStartTime().equals(minute)) {
				// 1.LEO-LEO 左右
				if (link.getFromNode().contains("LEO") && link.getEndNode().contains("LEO")) {
					if (link.getEndNode().charAt(3) - link.getFromNode().charAt(3) == 1) {//拿到当前endnode节点的上一行所有节点zk
						// 动态链路，一个节点只能右边与他平行的和上下的节点进行通信
						if (link.getEndNode().charAt(4) - link.getFromNode().charAt(4) <= 1  //拿到当前endnode节点上一行离endnode最近的三个节点zk
								&& link.getEndNode().charAt(3) - link.getFromNode().charAt(3) >= -1) {
							minuteLEOToLEOLinks.add(link);
						}
					}
				} else if ((link.getFromNode().contains("LEO") && link.getEndNode().contains("GEO"))
						|| (link.getFromNode().contains("GEO") && link.getEndNode().contains("LEO"))) {
					// 2.LEO-GEO
					minuteLEOToGEOLinks.add(link);
				} else if ((link.getFromNode().contains("LEO") && link.getEndNode().contains("Aircraft"))
						|| (link.getFromNode().contains("Aircraft") && link.getEndNode().contains("LEO"))) {
					// 3.LEO-Aircraft
					minuteLEOToAirLinks.add(link);
				} else if ((link.getFromNode().contains("LEO") && link.getEndNode().contains("Ground"))
						|| (link.getFromNode().contains("Ground") && link.getEndNode().contains("LEO"))) {
					// 4.LEO-Ground
					minuteLEOToGroLinks.add(link);
				}
			}
		}

		ArrayList<STKLink> list = new ArrayList<>();
		list.addAll(getDynamicLEOTopology(minuteLEOToLEOLinks));
		list.addAll(getDynamicLEOTopology(minuteLEOToGEOLinks));
		list.addAll(getStaticLEOTopology(minuteLEOToAirLinks));
		list.addAll(getStaticLEOTopology(minuteLEOToGroLinks));
		return list;
	}
	
	static class DynamicComparator implements Comparator<STKLink> {

		@Override
		public int compare(STKLink o1, STKLink o2) {
			if (o1.getFromNode().equals(o2.getFromNode())) {
				return Integer.parseInt(o1.getRange()) - Integer.parseInt(o2.getRange());
			} else {
				return o1.getFromNode().compareTo(o2.getFromNode());
			}
		}
	}
	
	static class StaticComparator implements Comparator<STKLink> {
		@Override
		public int compare(STKLink o1, STKLink o2) {
			if (o1.getEndNode().equals(o2.getEndNode())) {
				return Integer.parseInt(o1.getRange()) - Integer.parseInt(o2.getRange());
			} else {
				return o1.getEndNode().compareTo(o2.getEndNode());
			}
		}

	}
	
	private static ArrayList<STKLink> getStaticLEOTopology(TreeSet<STKLink> set) {
		ArrayList<STKLink> list = new ArrayList<>();
		if (!set.isEmpty()) {
			Iterator<STKLink> minIterator = set.iterator();

			STKLink first = minIterator.next();
			list.add(first);

			while (minIterator.hasNext()) {
				STKLink next = minIterator.next();
				if (!next.getEndNode().equals(first.getEndNode())) {
					first = next;
					list.add(next);
				}
			}
		}
		return list;
	}
	
	private static ArrayList<STKLink> getDynamicLEOTopology(TreeSet<STKLink> set) {
		ArrayList<STKLink> list = new ArrayList<>();
		if (!set.isEmpty()) {
			Iterator<STKLink> minIterator = set.iterator();

			STKLink first = minIterator.next();
			list.add(first);

			while (minIterator.hasNext()) {
				STKLink next = minIterator.next();
				if (!next.getFromNode().equals(first.getFromNode())) {
					first = next;
					list.add(next);
				}
			}
		}
		return list;
	}
	
	
	@Test
	public void demo1(){
		UserDaoImpl userdao = new UserDaoImpl();
		Project project = new Project();
		project.setProjectName("wocao");
	}
}
