package cn.edu.uestc.platform.test.newTest1;

import java.util.TreeSet;

import cn.edu.uestc.platform.dynamicChange.Filters;
import cn.edu.uestc.platform.dynamicChange.NewLink;

public class DynamicTest {

	public static void main(String[] args) {
		TreeSet<NewLink> linksLR = Filters.readMinuteMEORL(0 + "");// 拿到当前分钟的链路
		System.out.println(linksLR);
	}
}
