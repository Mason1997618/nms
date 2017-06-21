package cn.edu.uestc.platform.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.User;
import cn.edu.uestc.platform.service.ServiceImpl;
import cn.edu.uestc.platform.utils.JSoneUtils;

//@Controller
public class Actiontest {

//	@RequestMapping("/login.action")
//	public String login(Model model, User user) {
//
//		System.out.println(user);
//		model.addAttribute("message", "用户名或者密码错误!");
//
//		return "login.jsp";
//
//	}
	
	
	@Test
	public void demo1(){
		User user = new User();
		user.setU_id(1);
		ServiceImpl service = new ServiceImpl();
		List<Project> projects = service.findAllProjectByUserId(user);
		System.out.println(JSoneUtils.ListToJson(projects).toString());

	}

}
