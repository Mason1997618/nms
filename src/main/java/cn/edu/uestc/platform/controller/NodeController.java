package cn.edu.uestc.platform.controller;

import org.openstack4j.api.OSClient.OSClientV3;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.uestc.platform.factory.OSClientFactory;
import cn.edu.uestc.platform.factory.ServerFactory;
import cn.edu.uestc.platform.utils.Constants;

@Controller
@RequestMapping("/node")
public class NodeController {
	/**
	 * 添加虚拟机节点
	 * 
	 * @param name
	 *            虚拟机节点名称
	 * @param ip
	 *            通过SSH进入虚拟机的ip地址，应该为192.168.5.0网段
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> createNode(String name, String ip, String zone) {
		OSClientV3 os = OSClientFactory.authenticate("zph", "123456", Constants.ZPH_PROJECT_ID);
		System.out.println(os);
		if(zone.equals("vm")){
			System.out.println("生成虚拟机节点");
			ServerFactory.createServer(os, name, ip);
		}else if(zone.equals("docker")){
			ServerFactory.createDockerServer(os, name, ip);
		}
		return ResponseEntity.ok("生成节点成功");
	}
	
}
