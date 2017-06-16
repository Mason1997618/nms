package cn.edu.uestc.platform.test.newTest;

import org.junit.Test;

import cn.edu.uestc.platform.dynamicChange.DynamicFactory;
import cn.edu.uestc.platform.dynamicChange.DynamicNetWorkUtils;
import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.factory.ServerFactory;
import cn.edu.uestc.platform.utils.Constants;

public class Date110 {

	// 测试生成节点
	@Test
	public void fun() {
		ServerFactory.createServer(OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID), "VM3",
				"192.168.5.6");
	}
	
	//测试生成网段
	@Test
	public void fun1(){
		DynamicNetWorkUtils.createSubnetByIP("10.20.10.1");
	}
	
	//测试添加端口并修改配置文件
	@Test
	public void fun2(){
		DynamicFactory.addPort(OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID), "VM3", "10.20.10.5");
	}
	
	//测试删除端口并修改配置文件
	@Test
	public void fun3(){
		DynamicFactory.delPort(OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID), "VM1", "10.20.10.4");
	}
}
