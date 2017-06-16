package cn.edu.uestc.platform.test.newTest1;

import cn.edu.uestc.platform.controller.LinkController;

public class BingXingTest {

	//测试链路的并行性质	
	public static void main(String[] args) {
		new LinkController().createLinkMTM("vm1", "10.30.10.13", "vm2", "10.30.10.14");
	}
}
