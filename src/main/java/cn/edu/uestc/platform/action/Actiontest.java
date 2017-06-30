package cn.edu.uestc.platform.action;

import java.util.List;

import org.junit.Test;
import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.network.Subnet;

import cn.edu.uestc.platform.controller.LinkController;
import cn.edu.uestc.platform.controller.NodeController;
import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.utils.Constants;

//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.codec.binary.StringUtils;
//import net.sf.json.JSONArray;
//import org.junit.Test;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import cn.edu.uestc.platform.pojo.Project;
//import cn.edu.uestc.platform.pojo.User;
//import cn.edu.uestc.platform.utils.JSoneUtils;

//@Controller
public class Actiontest {

	@Test
	public void linkTest() {
		LinkController controller = new LinkController();

		controller.createLinkMTM("可以哟", "192.168.8.6", "nodeTest2", "192.168.8.7");
	}

	@Test
	public void NodeTest(){
		NodeController controller = new NodeController();
		controller.createNode("nodeTest1", "192.168.10.4", "vm");
		controller.createNode("nodeTest2", "192.168.10.5", "vm");
//		
//LinkController controller = new LinkController();
//		
//		controller.createLinkMTM(nodeTest1, 192.168.10.2, toNode, toNodeIP);

		
	
	}
}
