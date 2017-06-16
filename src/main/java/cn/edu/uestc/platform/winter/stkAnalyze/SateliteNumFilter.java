package cn.edu.uestc.platform.winter.stkAnalyze;

import java.util.Set;

/**
 * 卫星数量分析
 */
public class SateliteNumFilter {

	private static Set<String> allNodes = STKFilters.getAllNodes("abc.csv");

	public static int getGEONum() {
		int i = 0;
		for (String node : allNodes) {
			if (node.contains("GEO")) {
				i++;
			}
		}
		return i;
	}

	public static int getLEONum() {
		int i = 0;
		for (String node : allNodes) {
			if (node.contains("LEO")) {
				i++;
			}
		}
		return i;
	}

	public static int getLEORowNum() {
		int i = 0;
		for (String node : allNodes) {
			if (node.contains("LEO")) {
				if (Integer.parseInt(node.charAt(4) + "") > i) {
					i = Integer.parseInt(node.charAt(4) + "");
				}
			}
		}
		return i;
	}

	public static int getLEOColumnNum() {
		int i = 0;
		for (String node : allNodes) {
			if (node.contains("LEO")) {
				if (Integer.parseInt(node.charAt(3) + "") > i) {
					i = Integer.parseInt(node.charAt(3) + "");
				}
			}
		}
		return i;
	}

	public static int getGroNum() {
		int i = 0;
		for (String node : allNodes) {
			if (node.contains("Ground")) {
				i++;
			}
		}
		return i;
	}

	public static int getAirNum() {
		int i = 0;
		for (String node : allNodes) {
			if (node.contains("Aircraft")) {
				i++;
			}
		}
		return i;
	}

	public static void main(String[] args) {
		System.out.println(getLEOColumnNum());
	}
}
