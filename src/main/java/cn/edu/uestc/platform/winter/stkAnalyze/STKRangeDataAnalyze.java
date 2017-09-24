package cn.edu.uestc.platform.winter.stkAnalyze;

import java.io.FileReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import cn.edu.uestc.platform.winter.pojo.RelativeLink;
import cn.edu.uestc.platform.winter.pojo.STKLink;
import cn.edu.uestc.platform.winter.utils.DateFormatter;

/**
 * 从一个文件中读取STK的数据，去除重复的数据，为了保证速度，将其加载到内存中
 */
public class STKRangeDataAnalyze {
	// 存放所有数据,并重复数据
	private static LinkedHashSet<STKLink> allData = new LinkedHashSet<>();// 所有的链路信息
	private static Set<RelativeLink> indexLink = new HashSet<>();// 所有的节点信息

	// 将数据加载到内存中
	private static void loadData(String path) {
		try {
			// 1.拿到STK导出的所有范围变化数据表
			Reader in = new FileReader(path);
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
			String fromIndex = "";
			String endIndex = "";
			for (CSVRecord record : records) {
				String text = record.get("Strand Name");
				String fromNode = text.substring(text.indexOf("/") + 1, text.indexOf(" "));
				String endNode = text.substring(text.lastIndexOf("/") + 1, text.length());
				String range = record.get("Range (km)").substring(0, record.get("Range (km)").indexOf("."));
				if (fromNode.equals(fromIndex) && endNode.equals(endIndex)) {
					allData.add(new STKLink(fromNode, endNode,
							DateFormatter.changeDateFormat(record.get("Time (UTCG)")), range));
				} else {
					RelativeLink link = new RelativeLink(fromNode, endNode);
					if (indexLink.contains(link)) {//// 这个contain的含义是什么？如何判断indexlink中是含有link的，contain是用来判断A与B和B与A是同一条链路的吗？
						continue;
					} else {
						fromIndex = fromNode;
						endIndex = endNode;
						indexLink.add(link);
						allData.add(new STKLink(fromNode, endNode,
								DateFormatter.changeDateFormat(record.get("Time (UTCG)")), range));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取所有不重复的链路信息
	 */
	public static LinkedHashSet<STKLink> getAllLinks(String path) {
		if (allData.size() == 0) {
			loadData(path);
		}
		return allData;
	}

	/**
	 * 获取所有不重复的点
	 */
	public static Set<RelativeLink> getIndexLink(String path) {
		if (allData.size() == 0) {
			loadData(path);
		}
		return indexLink;
	}
}
