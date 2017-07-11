package cn.edu.uestc.platform.action;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.uestc.platform.service.LinkService;
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
	@RequestMapping("#")
	@ResponseBody
	public String deletePort(int pt_id) {
		PortService service = new PortService();
		service.deletePort(pt_id);
		return "删除成功！";
	}
}
