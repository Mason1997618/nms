package cn.edu.uestc.platform.utils;

import java.util.List;

public class DataFormatter {

	// 执行网络替换功能
	public static String[] changeNetwork(List<String> sortedIps) {
		String[] comds = new String[7];
		// 执行虚拟机网卡文件的替换工作
		comds[0] = "echo -e 'auto lo\niface lo inet loopback";
		// zebra文件的替换
		comds[1] = "echo -e 'hostname ospfd\npassword zebra\nlog stdout\nrouter ospf\n";
		//comds[1] = "echo -e 'hostname ripd\npassword zebra\nlog stdout\nrouter rip\n";
		comds[2] = "echo -e 'hostname zph\npassword zebra\n";
		for (int i = 0; i < sortedIps.size(); i++) {
			comds[0] += "\nauto eth" + (i + 1) + "\niface eth" + (i + 1) + " inet static\naddress " + sortedIps.get(i)
					+ "\nnetmask 255.255.255.0";
			comds[1] += " network " + sortedIps.get(i).substring(0, sortedIps.get(i).lastIndexOf(".")) + ".0/24 area 0\n";
			//comds[1] += " network " + sortedIps.get(i).substring(0, sortedIps.get(i).lastIndexOf(".")) + ".0/24\n";
			comds[2] += "interface eth" + (i + 1) + "\n no link-detect\n ip address " + sortedIps.get(i)
					+ "/24\n ipv6 nd suppress-ra\n";
		}
		comds[0] += "' >interfaces";
		comds[1] += "line vty\nend' >>ospfd.conf";
		//comds[1] += "line vty' >>ripd.conf";
		comds[2] += "interface lo\n no link-detect\nip forwarding\nline vty' >zebra.conf";

		comds[3] = "sudo mv ospfd.conf /etc/quagga/ospfd.conf";
		//comds[3] = "sudo mv ospfd.conf /etc/quagga/ripd.conf";
		comds[4] = "sudo mv interfaces /etc/network/interfaces";
		comds[5] = "sudo mv zebra.conf /etc/quagga/zebra.conf";
		comds[6] = "sudo reboot";
		return comds;
	}

}
