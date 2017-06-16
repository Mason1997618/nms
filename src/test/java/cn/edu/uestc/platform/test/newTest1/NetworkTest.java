package cn.edu.uestc.platform.test.newTest1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

//测试java获取网卡地址
//检测思路：运行在虚拟机的内部，开机自动启动，每隔2s钟检测一下网卡是否变化，这里的变化分为两部分 1.是否增加删除网卡 2.增加的网卡的IP地址是多少 
//执行zebra程序
public class NetworkTest {

	public static void main(String[] args) throws Exception {

	}

	// 执行Shell脚本程序  String command = "/home/test.sh";
	public static void execShell(String command) {
		InputStreamReader stdlSR = null;
		InputStreamReader errlSR = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			String line = null;
			stdlSR = new InputStreamReader(process.getInputStream());
			BufferedReader stdBR = new BufferedReader(stdlSR);
			while ((line = stdBR.readLine()) != null) {
				System.out.println("STD line: " + line);
			}
			errlSR = new InputStreamReader(process.getErrorStream());
			BufferedReader errBR = new BufferedReader(errlSR);
			while ((line = errBR.readLine()) != null) {
				System.out.println("ERR line: " + line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(stdlSR!=null){
					stdlSR.close();
				}
				if (errlSR!=null) {
					errlSR.close();
				}
				if(process!=null){
					process.destroy();
				}
			}catch (IOException e) {
				System.out.println("执行命令："+command+" 有IO异常");
			}
		}
	}

	// 获取当前时间的网卡配置信息
	public static HashMap<String, String> getNetworkInfo(String time) throws SocketException {
		HashMap<String, String> ethInfo = new HashMap<>();
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			NetworkInterface ni = interfaces.nextElement();

			if (ni.getName().contains("eth")) {
				List<InterfaceAddress> ia = ni.getInterfaceAddresses();
				if (!ia.isEmpty()) {
					for (InterfaceAddress i : ia) {
						String address = i.getAddress().getHostAddress();
						if (address.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
							ethInfo.put(ni.getName(), address);
							break;
						}
					}
				}
			}
		}
		System.out.println(ethInfo);
		return ethInfo;
	}
}
