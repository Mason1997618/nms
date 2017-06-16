package cn.edu.uestc.platform.test.newTest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

import cn.edu.uestc.platform.dynamicChange.NewLink;

public class Gra44Filters {

	/**
	 * 过滤器，拿到指定分钟的 东西 方向的制定最短拓扑图
	 * 
	 * @param minute
	 *            指定分钟
	 */
	public static TreeSet<NewLink> readMinuteMEORL(String minute) {
		LinkedHashSet<NewLink> allLink = new LinkedHashSet<>(Gra44ReadRangeFactory.readAllLink());

		// 过滤器，定制规则，删除多余链路,只留下LEO之间的链路=>艹，还必须用迭代器进行操作，不能用范围for循环
		Iterator<NewLink> iterator = allLink.iterator();
		while (iterator.hasNext()) {
			NewLink li = iterator.next();
			if (!li.getFrom().contains("LEO") || !li.getEnd().contains("LEO")) {
				iterator.remove();
			}
		}

		// 过滤器，2.选取可能存在的链路,LEO上下的肯定相连(除去上下的部分)，左右的相邻卫星段相连
		iterator = allLink.iterator();
		while (iterator.hasNext()) {
			NewLink li = iterator.next();
			int first = Integer
					.parseInt(li.getFrom().substring(li.getFrom().indexOf("O") + 1, li.getFrom().indexOf("O") + 2));
			int last = Integer
					.parseInt(li.getEnd().substring(li.getEnd().indexOf("O") + 1, li.getEnd().indexOf("O") + 2));
			if (last - first != 1) {
				iterator.remove();
			}
		}

		// 完成过滤器，此时 link中的为MEO所有的链接
		TreeSet<NewLink> minuteLink = new TreeSet<>(new Comparator<NewLink>() {
			@Override
			public int compare(NewLink o1, NewLink o2) {
				if (o1.getFrom().equals(o2.getFrom())) {
					return Integer.parseInt(o1.getRange()) - Integer.parseInt(o2.getRange());
				} else {
					return o1.getFrom().compareTo(o2.getFrom());
				}
			}
		}); // 按照指定分钟 选取当前分钟的所有MEO的链接
		for (NewLink link : allLink) {
			if (link.getStartTime().equals(minute)) { // 这里是时间
				// System.out.println(link);
				minuteLink.add(link);
			}
		}
		// 该分钟内 shortLink 选取一个点与右点距离最近的路线
		Iterator<NewLink> minIterator = minuteLink.iterator();
		NewLink first = minIterator.next();
		while (minIterator.hasNext()) {
			NewLink next = minIterator.next();
			if (next.getFrom().equals(first.getFrom())) {
				minIterator.remove();
			} else {
				first = next;
			}
		}
		return minuteLink;
	}
}
