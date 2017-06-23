package cn.edu.uestc.platform.action;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.pojo.User;
import cn.edu.uestc.platform.service.ServiceImpl;
import cn.edu.uestc.platform.utils.JSoneUtils;

@Controller
public class ActionController {
	
	
	/*
	 * 用户注册
	 * 
	 */
	@RequestMapping("/register.action")
	public String userRegister(Model model, User user) {
		ServiceImpl service = new ServiceImpl();
		if (service.userRegister(user) == false) {
			System.out.println("用户名已经存在");
			model.addAttribute("message", "用户名已经存在");
			return "##跳转到错误信息提示页面";
		}
		System.out.println("success!");
		model.addAttribute("message", "注册成功");
		return "/login"; // 提示信息后 跳转到用户登录界面

	}

	
	/*
	 * 用户登陆
	 * 
	 */
	@RequestMapping("/login.action")
	public String userLogin(Model model, User user, HttpSession session) {

		ServiceImpl service = new ServiceImpl();
		// System.out.println(service.userLogin(user));

		if (service.userLogin(user).getU_id() == 0) {
			System.out.println("用户未注册");
			model.addAttribute("message", "用户未注册");
			return "register";
		}
		if (!service.userLogin(user).getPsw().equals(user.getPsw())) {
			System.out.println("密码错误！");
			model.addAttribute("message", "密码错误");
			return "login";
		}
		session.setAttribute("user", service.userLogin(user));
		model.addAttribute("message", "登录成功！");
		return "detailProject";
	}

	
	/*
	 * 新建工程
	 * 
	 */
	@ResponseBody
	@RequestMapping("/creatProject")
	public String createProject(Model model, Project project, HttpSession session) {

		User user = (User) session.getAttribute("user");
		System.out.println(user);
		project.setUser_id(user.getU_id());// 将当前用户uid给要创建的工程
		ServiceImpl service = new ServiceImpl();
		if (service.createProject(project) == true) {
			System.out.println("创建成功！");
			return "创建成功!";
		} else {
			System.out.println("创建失败！");
			return "工程名已存在,请重新输入。";
		}

	}

	/*
	 * 查找当前用户下的所有工程
	 */
	@RequestMapping("/selectProjectList")
	@ResponseBody
	public String selectProjectList(Model model, HttpSession session) {
		ServiceImpl service = new ServiceImpl();
		User user = (User) session.getAttribute("user");
		List<Project> projects = service.findAllProjectByUserId(user);
		//System.out.println(JSoneUtils.ListToJson(projects).toString());
		return JSoneUtils.ListToJson(projects).toString();
	}

	
	/*
	 * 创建场景
	 */
	@RequestMapping("/createScenario")
	@ResponseBody
	public String createScenario(Model model, Scenario scenario,int p_id) {
		ServiceImpl service = new ServiceImpl();
		// 现在假设能从前端拿到场景所属的工程id号。
		scenario.setProject_id(p_id);
		if (service.createScenario(scenario) == false) {
			return "场景名已经存在！";
		}
		return "创建成功！！";
	}

	
	/*
	 * 根据工程id 查找所有场景
	 */
	@RequestMapping("/selectScenarioList")
	@ResponseBody
	public String selectScenarioList(int p_id) {
		ServiceImpl service = new ServiceImpl();
		List<Scenario> scenarios = service.findAllScenarioByProjectId(p_id);
	//	System.out.println(JSoneUtils.ListToJson(scenarios).toString());
		System.out.println("已经获取到了场景列表");
		System.out.println(JSoneUtils.ListToJson(scenarios).toString());
		return JSoneUtils.ListToJson(scenarios).toString();
	}

	
	/*
	 * 创建节点,需要底层启动虚拟机，数据库中不但需插入节点数据，相应的场景的节点总数+1,判断完是简单节点还是复杂节点之后对应类型数目+1
	 */
	@RequestMapping("/createNode")
	@ResponseBody
	public String createNode(Node node,int s_id){
		ServiceImpl service = new ServiceImpl();
		boolean flag = service.createNode(node,s_id);
		if(flag==true){
			return "创建节点成功！";
		}
		return "创建节点失败！";
	}
	
	
	/*
	 * 根据场景返回所有节点
	 */
	
	@RequestMapping("/selectNodeList")
	@ResponseBody
	public String selectNodeList(int s_id){
		ServiceImpl service = new ServiceImpl();
		List<Node> nodes = service.findAllNodeByScenarioId(s_id);
		return JSoneUtils.ListToJson(nodes).toString();
	}
}
