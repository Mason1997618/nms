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
	public void demo1() {

//		NodeController controller = new NodeController();
//		controller.createNode("zktest3", "192.168.10.33", "vm");
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		LinkController link = new LinkController();
		link.createLinkMTM("zktest2", "192.168.7.6", "zktest3", "192.168.7.7");
		
	}
}
