package cn.edu.uestc.platform.testzk;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.Test;

import cn.edu.uestc.platform.pojo.BigClassForFilter;
import cn.edu.uestc.platform.pojo.LinkForFilter;
import cn.edu.uestc.platform.utils.JSoneUtils;

public class ZWNTest {

	public static void main(String[] args) throws IOException {
		// 拿到不重复的链路
		Set<LinkForFilter> set = demo1();
		// 用来存放多个大类
		Set<BigClassForFilter> bigClassForFilters = new HashSet<>();
		// 用来放不重复的大类名字
		List<String> fromBigNodeNames = new ArrayList<>();
		// 定义字符串 用来拼接索引（大类之间的连接靠它判断）
		List<String> relation = new ArrayList<>();
		for (LinkForFilter s : set) {
			String bigFromNodeName = s.getFromNodeName();
			String bigTonodeName = s.getToNodeName();

			char[] buf1 = bigFromNodeName.toCharArray();
			char[] buf2 = bigTonodeName.toCharArray();
			int i = 0;
			String str1 = "";
			while (i < buf1.length) {
				if (buf1[i] > '0' && buf1[i] < '9') {
					break;
				}
				str1 += buf1[i];
				i++;
			}
			if (!fromBigNodeNames.contains(str1)) {
				fromBigNodeNames.add(str1);
				BigClassForFilter bigClassForFilter = new BigClassForFilter();
				bigClassForFilter.setFromBigNodeName(str1);
				bigClassForFilters.add(bigClassForFilter);
			}

			i = 0;
			String str2 = "";
			while (i < buf2.length) {
				if (buf2[i] > '0' && buf2[i] < '9') {
					break;
				}
				str2 += buf2[i];
				i++;
			}
			String tmp = str1 + ":" + str2;
			if (!relation.contains(tmp)) {
				relation.add(tmp);
			}
		}
		// System.out.println(relation);

		// 给每个大类添加与之关联的大类
		for (BigClassForFilter b : bigClassForFilters) {
			// 拿到当前大类的节点名字
			String curBigNodeName = b.getFromBigNodeName();

			for (String rel : relation) {
				if (!rel.substring(0, rel.indexOf(":")).equals(curBigNodeName)) {
					continue;
				} else {
					String tmm = rel.substring(rel.indexOf(":") + 1, rel.length());
					// System.out.println(tmm);
					if (b.getToBigNodeNames() == null) {
						b.getToBigNodeNames().add(tmm);
					} else if ((b.getToBigNodeNames().contains(tmm))) {
						continue;
					} else if (!b.getToBigNodeNames().contains(tmm)) {
						b.getToBigNodeNames().add(rel.substring(rel.indexOf(":") + 1, rel.length()));
					}
				}
			}
		}

		// 为每个大类节点添加对应的子节点
		for (LinkForFilter s : set) {
			for (BigClassForFilter b : bigClassForFilters) {
				if (s.getFromNodeName().indexOf(b.getFromBigNodeName()) == -1) {
					continue;
				} else {
					b.getInnerNodeName().add(s.getFromNodeName());
				}
			}
		}
		
		
//		for(BigClassForFilter b : bigClassForFilters){
//			System.out.println("大类有: "+b.getFromBigNodeName());
//			System.out.println("大类中的子类有:" + b.getInnerNodeName());
//			System.out.println("与大类连接的有:" + b.getToBigNodeNames());
//		}
		
		System.out.println(JSoneUtils.SetToJson(set));
		
	}

	@Test
	public static Set<LinkForFilter> demo1() throws IOException {
		Reader in = new FileReader("interval5min.csv");
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);

		Set<LinkForFilter> set = new HashSet<LinkForFilter>();

		for (CSVRecord record : records) {
			LinkForFilter link4fltr = new LinkForFilter();
			String fromNodeName = record.get("Strand Name").substring(record.get("Strand Name").indexOf("/") + 1,
					record.get("Strand Name").indexOf(" "));
			String toNodeName = record.get("Strand Name").substring(record.get("Strand Name").lastIndexOf("/") + 1,
					record.get("Strand Name").length());
			link4fltr.setFromNodeName(fromNodeName);
			link4fltr.setToNodeName(toNodeName);
			set.add(link4fltr);
		}
		return set;
	}

	@Test
	public void demo2() throws IOException {
		Set<LinkForFilter> set = this.demo1();
		for (LinkForFilter s : set) {
			System.out.println(s);
		}

		// Reader in = new FileReader("interval5min.csv");
		// Iterable<CSVRecord> records =
		// CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
		// List<BigClassForFilter> bigClassForFilters = new ArrayList<>();
		// for (CSVRecord record : records) {
		// String curFromNodeName = record.get("Strand
		// Name").substring(record.get("Strand Name").indexOf("/") + 1,
		// record.get("Strand Name").indexOf(" "));
		// String curToNodeName = record.get("Strand
		// Name").substring(record.get("Strand Name").lastIndexOf("/") + 1,
		// record.get("Strand Name").length());
		//
		// // 1、先把大类有哪些装入集合
		// char[] buf = curFromNodeName.toCharArray();
		// int i = 0;
		//
		// String str = "";
		// while (i < buf.length) {
		// if (buf[i] > '0' && buf[i] < '9') {
		// break;
		// }
		// str += buf[i];
		// i++;
		// }
		// // System.out.println(str);
		// int isfitst = 1;
		// if(isfitst==1){
		// BigClassForFilter bigClassForFilter = new BigClassForFilter();
		// bigClassForFilter.setFromBigNodeName(str);
		// bigClassForFilters.add(bigClassForFilter);
		// isfitst=0;
		// }
		// for (BigClassForFilter b : bigClassForFilters) {
		// if (b.getFromBigNodeName().equals(str)) {
		// continue;
		// }
		// System.out.println("setiing");
		// BigClassForFilter bigClassForFilter = new BigClassForFilter();
		// bigClassForFilter.setFromBigNodeName(str);
		// bigClassForFilters.add(bigClassForFilter);
		// }
		// }
		// System.out.println(bigClassForFilters);
	}
	//
	//
	// // 去掉字符串后面的数字（拿到大类名字）
	//
	// // 判断集合中的的大类中是否有这个名字
	// for (BigClassForFilter b : bigClassForFilters) {
	// if (b.getFromBigNodeName().equals(str)) {
	// continue;
	// } else {
	// // 把大类的名字存进去
	// bigClassForFilter.setFromBigNodeName(str);
	// }
	//
	// }
	//
	// // 存子节点的名字进大集合
	// Set<String> innerNodeName = new HashSet<String>();
	// innerNodeName.add(curNodeName);
	// bigClassForFilter.setInnerNodeName(innerNodeName);
	// bigClassForFilters.add(bigClassForFilter);

	@Test
	public void TestList() {
		// List<LinkForFilter> links = new ArrayList<>();
		Set<LinkForFilter> set = new HashSet<LinkForFilter>();
		LinkForFilter link4fltr1 = new LinkForFilter();
		link4fltr1.setFromNodeName("1");
		link4fltr1.setToNodeName("2");

		LinkForFilter link4fltr2 = new LinkForFilter();
		link4fltr2.setFromNodeName("2");
		link4fltr2.setToNodeName("1");
		set.add(link4fltr1);
		set.add(link4fltr2);
		System.out.println(set.size());
	}
	// @Test
	// public void demo3() {
	// Set<String> set = new HashSet<String>();
	// set.add("sfdfds");
	// set.add("sfdfds");
	// set.add("sfdfds");
	// set.add("sfdfds");
	// set.add("sfdfds");
	// System.out.println(set.size());
	//
	// }

}
