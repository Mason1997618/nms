package cn.edu.uestc.platform.dao;

import org.junit.Test;

import cn.edu.uestc.platform.pojo.Link;
import cn.edu.uestc.platform.pojo.Node;
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
		node.setNodeName("zty1");
		node.setS_id(15);
		System.out.println(dao.haveNodeName(node));
	}

	@Test
	public void demo10() {
		PortDao dao = new PortDaoImpl();
		// System.out.println(dao.getPortByID(43));
		// Link link = new Link();
		// link.setTxPort_id(26);
		// link.setRxPort_id(27);
		// System.out.println(dao.getPortByLink(link));

		// List<Port> ports = dao.getPortListBynodeName(20, "test5");
		// for (Port p : ports) {
		// System.out.println(p);
		// }
		// Link link = new Link();
		// link.setL_id(3);
		// int arr[] = dao.getPortIdsByLink(link);
		// for(int i=0;i<arr.length;i++){
		// System.out.println(arr[i]);
		// }
	}

	@Test
	public void demo11() {
		LinkDao dao = new LinkDaoImpl();
		// dao.updateLinkStatustoDown(25, "1232");
		// System.out.println(dao.getLinkByPortID(45));
		// System.out.println(dao.getLink(18, "testLInkDemo1"));
		// System.out.println(dao.getLinkList(13));
	}

}