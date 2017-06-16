package cn.edu.uestc.platform.winter.stkAnalyze;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 写好每个端口的静态的IP，GEO->GEO / LEO->LEO / GEO->LEO / GEO->Ground / LEO->Aircraft /
 * LEO->Ground
 */
public class IPDynamicCreateFactory {
	/**
	 * 生成 GEO对GEO 卫星表，规则：任意两个GEO属于不同网段，GEO的网段为10.10.1.4开头
	 * 
	 * @param number
	 *            GEO卫星的数量
	 */
	public static String[][] createTableGEOToGEO(int number) {
		int index = 0;
		String[][] IPTable = new String[number * (number - 1) / 2][4];
		for (int i = 1; i < number; i++) {
			for (int j = i + 1; j <= number; j++) {
				IPTable[index] = new String[] { "GEO" + i, "GEO" + j, "10.10." + (index + 1) + ".4",
						"10.10." + (index + 1) + ".5" };
				index++;
			}
		}
		return IPTable;
	}

	/**
	 * 生成 LEO对LEO 卫星表（左右），规程：任意两个LEO属于不同网段，LEO对LEO的网段为10.20.1.4开头
	 * 
	 * @param row
	 *            卫星 行
	 * @param column
	 *            卫星 列
	 */
	public static String[][] createTableLEOToLEOLR(int row, int column) {
		int index = 0;
		String[][] IPTable = new String[row * row * (column - 1)][4];
		for (int i = 1; i < column; i++) {
			for (int j = 1; j <= row; j++) {
				for (int m = 1; m <= row; m++) {
					IPTable[index] = new String[] { "LEO" + i + m, "LEO" + (i + 1) + j, "10.20." + (index + 1) + ".4",
							"10.20." + (index + 1) + ".5" };
					index++;
				}
			}
		}
		return IPTable;

	}

	/**
	 * 生成 LEO对LEO 卫星表（上下），规程：任意两个LEO属于不同网段，LEO对LEO的网段为10.30.1.4开头
	 * 
	 * @param row
	 *            卫星 行
	 * @param column
	 *            卫星 列
	 */
	public static String[][] createTableLEOToLEOUD(int row, int column) {
		int index = 0;
		String[][] IPTable = new String[column * (row - 1)][4];
		for (int i = 1; i <= column; i++) {
			for (int j = 1; j < row; j++) {
				IPTable[index] = new String[] { "LEO" + i + j, "LEO" + i + (j + 1), "10.30." + (index + 1) + ".4",
						"10.30." + (index + 1) + ".5" };
				index++;
			}
		}
		return IPTable;
	}

	/**
	 * 生成GEO对LEO GEO对LEO的网段为10.40.1.4开头(每个GEO为一个网段，参看前边的IPCreateFactory)
	 */
	public static String[][] createTableGEOToLEO(int GEONumber, int LEORow, int LEOColumn) {
		int index = 0;
		String[][] IPTable = new String[GEONumber * LEOColumn * LEORow][4];
		for (int i = 1; i <= GEONumber; i++) {
			for (int j = 1; j <= LEORow; j++) {
				for (int m = 1; m <= LEOColumn; m++) {
					IPTable[index] = new String[] { "GEO" + i, "LEO" + m + j, "10.40." + i + ".4",
							"10.40." + i + "." + (index + 5) };
					index++;
				}
			}
		}
		return IPTable;
	}

	/**
	 * 生成GEO对Ground
	 * GEO对Ground的网段为10.50.1.4开头(每个GEO为一个网段)
	 */
	public static String[][] createTableGEOToGround(int GEONumber, int GroundNumber) {
		int index = 0;
		String[][] IPTable = new String[GEONumber * GroundNumber][4];
		for (int i = 1; i <= GEONumber; i++) {
			for (int j = 1; j <= GroundNumber; j++) {
				IPTable[index] = new String[] { "GEO" + i, "Ground" + j, "10.50." + i + ".4",
						"10.50." + i + "." + (index + 5) };
				index++;
			}
		}
		return IPTable;
	}
	
	/**
	 * 生成LEO对Aircraft
	 * LEO对Aircraft的网段为10.60.1.4开头(每个Aircraft为一个网段)
	 */
	public static String[][] createTableLEOToAir(int LEORow,int LEOColumn, int AirNumber) {
		int index = 0;
		String[][] IPTable = new String[LEORow*LEOColumn * AirNumber][4];
		for (int i = 1; i <= AirNumber; i++) {
			for (int j = 1; j <= LEORow; j++) {
				for (int m = 1; m <= LEOColumn; m++) {
					IPTable[index] = new String[] { "Aircraft" + i, "LEO" + m + j, "10.60." + i + ".4",
							"10.60." + i + "." + (index + 5) };
					index++;
				}
			}
		}
		return IPTable;
	}
	
	/**
	 * 生成LEO对Ground
	 * LEO对Ground的网段为10.70.1.4开头(每个Ground为一个网段)
	 */
	public static String[][] createTableLEOToGround(int LEORow,int LEOColumn, int GroNumber) {
		int index = 0;
		String[][] IPTable = new String[LEORow*LEOColumn * GroNumber][4];
		for (int i = 1; i <= GroNumber; i++) {
			for (int j = 1; j <= LEORow; j++) {
				for (int m = 1; m <= LEOColumn; m++) {
					IPTable[index] = new String[] { "Ground" + i, "LEO" + m + j, "10.70." + i + ".4",
							"10.70." + i + "." + (index + 5) };
					index++;
				}
			}
		}
		return IPTable;
	}
	
	//预先生成所有的网段，所以要在这里获取所有的网段
	public static Set<String> getAllLinks(){
		Set<String> bridges = new LinkedHashSet<>();
		//GEO对GEO
		for (String[] bridge : createTableGEOToGEO(SateliteNumFilter.getGEONum())) {
			bridges.add(bridge[2]);
		}
		//GEO对地面
		for (String[] bridge : createTableGEOToGround(SateliteNumFilter.getGEONum(), SateliteNumFilter.getGroNum())) {
			bridges.add(bridge[2]);
		}
		//LEO对地面
		for (String[] bridge : createTableLEOToGround(SateliteNumFilter.getLEORowNum(),SateliteNumFilter.getLEOColumnNum(),SateliteNumFilter.getGroNum())) {
			bridges.add(bridge[2]);
		}
		//LEO对Air
		for (String[] bridge : createTableLEOToAir(SateliteNumFilter.getLEORowNum(),SateliteNumFilter.getLEOColumnNum(),SateliteNumFilter.getAirNum())) {
			bridges.add(bridge[2]);
		}
		//GEO对LEO
		for (String[] bridge : createTableGEOToLEO(SateliteNumFilter.getGEONum(),SateliteNumFilter.getLEORowNum(),SateliteNumFilter.getLEOColumnNum())) {
			bridges.add(bridge[2]);
		}
		//LEO对LEO UD
		for (String[] bridge : createTableLEOToLEOUD(SateliteNumFilter.getLEORowNum(),SateliteNumFilter.getLEOColumnNum())) {
			bridges.add(bridge[2]);
		}
		//LEO对LEO LR
		for (String[] bridge : createTableLEOToLEOLR(SateliteNumFilter.getLEORowNum(),SateliteNumFilter.getLEOColumnNum())) {
			bridges.add(bridge[2]);
		}
		return bridges;
	} 
 
	public static void main(String[] args) {
//		String[][] a = createTableGEOToGround(3,2);
//		System.out.println(Arrays.deepToString(a));
		System.out.println(getAllLinks());
		System.out.println(getAllLinks().size());
	}
}
