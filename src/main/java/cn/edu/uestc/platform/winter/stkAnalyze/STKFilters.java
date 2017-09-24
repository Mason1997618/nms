package cn.edu.uestc.platform.winter.stkAnalyze;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import cn.edu.uestc.platform.winter.pojo.RelativeLink;
import cn.edu.uestc.platform.winter.pojo.STKLink;

/**
 * 数据过滤器，提取有用的信息
 */
public class STKFilters {

	/**
	 * 获取所有的节点
	 */
	public static Set<String> getAllNodes(String path) {
		Set<String> set = new HashSet<>();
		for (RelativeLink link : STKRangeDataAnalyze.getIndexLink(path)) {
			set.add(link.getNodeA());
			set.add(link.getNodeB());
		}
		return set;
	}

	/**
	 * 加载每分钟的所有的数据=====> 这是动态链路 ======> 不考虑静态的 动态的：LEO-LEO(左右) LEO-GEO
	 * LEO-Aircraft LEO-地面 静态的：GEO-GEO GEO-地面 LEO-LEO(上下)
	 */
	public static ArrayList<STKLink> getMinutesData(String minute) {
		TreeSet<STKLink> minuteLEOToLEOLinks = new TreeSet<>(new DynamicComparator());
		TreeSet<STKLink> minuteLEOToGEOLinks = new TreeSet<>(new DynamicComparator());
		TreeSet<STKLink> minuteLEOToAirLinks = new TreeSet<>(new StaticComparator());
		TreeSet<STKLink> minuteLEOToGroLinks = new TreeSet<>(new StaticComparator());
		for (STKLink link : STKRangeDataAnalyze.getAllLinks("abc.csv")) {  // 拿到当前的时间最完整的所有链路信息
			if (link.getStartTime().equals(minute)) {
				// 1.LEO-LEO 左右
				if (link.getFromNode().contains("LEO") && link.getEndNode().contains("LEO")) {
					if (link.getEndNode().charAt(3) - link.getFromNode().charAt(3) == 1) {
						// 动态链路，一个节点只能右边与他平行的和上下的节点进行通信
						if (link.getEndNode().charAt(4) - link.getFromNode().charAt(4) <= 1  
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

	/**
	 * 获取每分钟跟上一分钟比较 链路的变化
	 */
	public static Set<STKLink> getMinuteChange(ArrayList<STKLink> set1, ArrayList<STKLink> set2) {
		Set<STKLink> set1Cp = new HashSet<>(set1);
		Set<STKLink> set2Cp = new HashSet<>(set2);
		Iterator<STKLink> linksIter = set1Cp.iterator();
		while (linksIter.hasNext()) {
			STKLink link = linksIter.next();
			for (STKLink linkBefore : set2Cp) {
				// 如果link和linkBefore相同，则将link删除
				if ((link.getFromNode().equals(linkBefore.getFromNode())
						&& link.getEndNode().equals(linkBefore.getEndNode()))
						|| (link.getFromNode().equals(linkBefore.getEndNode())
								&& link.getEndNode().equals(linkBefore.getFromNode()))) {
					linksIter.remove();
				}
			}
		}
		return set1Cp;
	}

	/**
	 * 过滤器，拿到每分钟的最短路径拓扑图 LEO-LEO(左右) LEO-GEO
	 */
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

	/**
	 * 过滤器，拿到每分钟的最短路径拓扑图 LEO-Aircraft LEO-地面
	 */
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

	// LEO->LEO LEO->GEO
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

	// LEO->Air LEO->Ground
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

	/**
	 * 加载每分钟最短的路径
	 */

	// 测试
	public static void main(String[] args) {
		// System.out.println(getAllNodes("abc.csv"));
		// getMinutesData("2");

		for (int i = 1; i < 10; i++) {
			System.out.println(getMinuteChange(getMinutesData(i - 1 + ""), getMinutesData(i + "")));
		}
	}
}
