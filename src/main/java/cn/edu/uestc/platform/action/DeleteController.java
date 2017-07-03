package cn.edu.uestc.platform.action;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.uestc.platform.service.LinkService;

public class DeleteController {

	/*
	 * 删除链路
	 */
	@RequestMapping("/deleteLink")
	@ResponseBody
	public String deleteLink(int s_id, String linkName) {
		LinkService service = new LinkService();
		boolean flag = service.deleteLink(s_id, linkName);
		if(flag ==true){
			return "删除成功！";
		}
		else{
			return "删除失败！";
		}
	}
}
