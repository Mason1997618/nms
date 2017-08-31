package cn.edu.uestc.platform.dealwithstk;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import cn.edu.uestc.platform.dao.ScenarioDao;
import cn.edu.uestc.platform.dao.ScenarioDaoImpl;
import cn.edu.uestc.platform.pojo.BigClassForFilter;
import cn.edu.uestc.platform.pojo.LinkForFilter;

public class STKAnalyse {

	public Set<LinkForFilter> getLinkForFilter(int s_id) throws IOException {

		// 拿到此场景下的文件路径
		ScenarioDao scenarioDao = new ScenarioDaoImpl();
		String path = scenarioDao.findDynamicTopologyFileBySid(s_id);
		Reader in = new FileReader(path);
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

	public Set<BigClassForFilter> getBigNodeLink(int s_id) throws IOException {
		// 拿到不重复的链路
		Set<LinkForFilter> set = getLinkForFilter(s_id);
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
		return bigClassForFilters;
	}

}
