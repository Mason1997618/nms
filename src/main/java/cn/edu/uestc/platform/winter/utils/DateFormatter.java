package cn.edu.uestc.platform.winter.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	private static Date start;
	static {
		try {
			start = sdf.parse("2007/07/01 12:00:00.000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * text传来的格式为 2 Jul 2007 02:53:47.000 ，需要将其转化为距离 2007/07/01 12:00:00 的分钟数
	 */
	public static String changeDateFormat(String text) {
		String startTimeDate = "2007/07/0" + text.substring(0, 1)
				+ text.substring(text.lastIndexOf(" "), text.length());
		String startTime = "";
		try {
			Date date = sdf.parse(startTimeDate);
			startTime = Math.round(((double) (date.getTime() - start.getTime())) / 1000 / 60) + "";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}
}
