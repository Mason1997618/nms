package cn.edu.uestc.platform.test.newTest;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import cn.edu.uestc.platform.dynamicChange.NewLink;

public class Gra44ReadRangeFactory {
	private static LinkedHashSet<NewLink> allLink = new LinkedHashSet<>();

	static {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date start = sdf.parse("2007/07/01 12:00:00");

			Reader in = new FileReader("abc.csv");
			Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				String text = record.get("Strand Name");
				String range = record.get("Range (km)").substring(0, record.get("Range (km)").indexOf("."));
				String fromNode = text.substring(text.indexOf("/") + 1, text.indexOf(" "));
				String toNode = text.substring(text.lastIndexOf("/") + 1, text.length());

				String startTimeDate = "2007/07/0" + record.get("Time (UTCG)").substring(0, 1)
						+ record.get("Time (UTCG)").substring(record.get("Time (UTCG)").lastIndexOf(" "),
								record.get("Time (UTCG)").lastIndexOf("."));
				Date date = sdf.parse(startTimeDate);
				String startTime = Math.round(((double) (date.getTime() - start.getTime())) / 1000 / 60) + "";

				//在这里去除了重复链路
				allLink.add(new NewLink(fromNode, toNode, startTime, range));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//读取所有的链路信息
	public static LinkedHashSet<NewLink> readAllLink(){
		return allLink;
	}
}
