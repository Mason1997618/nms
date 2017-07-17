package cn.edu.uestc.platform.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.uestc.platform.service.LinkService;
import cn.edu.uestc.platform.service.NodeService;
import cn.edu.uestc.platform.service.PortService;

@Controller
public class DeleteController {

	/*
	 * 删除链路
	 */
	@RequestMapping("/deleteLink")
	@ResponseBody
	public String deleteLink(int s_id, String linkName) {
		System.out.println(s_id + "------" + linkName);
		LinkService service = new LinkService();
		service.deleteLink(s_id, linkName);
		return "删除成功！";
	}

	/*
	 * 删除端口
	 */
	@RequestMapping("/deletePort")
	@ResponseBody
	public String deletePort(int pt_id) {
		System.out.println(pt_id);
		PortService service = new PortService();
		service.deletePort(pt_id);
		return "删除成功！";
	}
	
	
	/*
	 * 删除节点
	 */
	@RequestMapping("/deleteNode")
	@ResponseBody
	public String deleteNode(int s_id,String nodeName){
		NodeService service = new NodeService();
		service.deleteNode(s_id,nodeName);
		return null;
	}
}
