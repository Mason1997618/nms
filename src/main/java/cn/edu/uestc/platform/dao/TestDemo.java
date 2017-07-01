package cn.edu.uestc.platform.dao;

import java.util.List;
import org.junit.Test;
import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Port;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.User;

public class TestDemo {

	@Test
	public void demo3() {
		UserDao userdao = new UserDaoImpl();
		User user = new User();
		user.setUsername("小赖");

		System.out.println(userdao.findByUserName(user));
	}

	@Test
	public void demo4() {
		ProjectDaoImpl pdao = new ProjectDaoImpl();
		Project project = new Project();
		project.setProjectName("updataname");
		project.setP_id(1);
		pdao.updataProjectName(project);
	}

	@Test
	public void demo5() {
		ScenarioDao scenario = new ScenarioDaoImpl();
		System.out.println(scenario.findAllScenarioByProjectId(7));
	}

	@Test
	public void dem9() {
		NodeDao dao = new NodeDaoImpl();
		Node node = new Node();
		dao.insertNode(node);
	}
	@Test
	public void demo10(){
		PortDao dao = new PortDaoImpl();
		List<Port> ports = dao.getPortListBynodeName(20, "test5");
		for(Port p :ports){
			System.out.println(p);
		}
	}
}
