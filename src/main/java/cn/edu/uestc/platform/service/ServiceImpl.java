package cn.edu.uestc.platform.service;

import java.util.List;

import cn.edu.uestc.platform.dao.NodeDao;
import cn.edu.uestc.platform.dao.NodeDaoImpl;
import cn.edu.uestc.platform.dao.ProjectDaoImpl;
import cn.edu.uestc.platform.dao.ScenarioDao;
import cn.edu.uestc.platform.dao.ScenarioDaoImpl;
import cn.edu.uestc.platform.dao.UserDao;
import cn.edu.uestc.platform.dao.UserDaoImpl;
import cn.edu.uestc.platform.pojo.Node;
import cn.edu.uestc.platform.pojo.Project;
import cn.edu.uestc.platform.pojo.Scenario;
import cn.edu.uestc.platform.pojo.User;

public class ServiceImpl implements IService {

	/*
	 * 用户登录
	 */

	public User userLogin(User user) {
		UserDao userdao = new UserDaoImpl();

		return userdao.findByUserName(user);
	}

	/*
	 * 用户注册
	 */
	public boolean userRegister(User user) {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDaoImpl();
		if (userdao.findByUserName(user).getU_id() == 0) {
			userdao.insertUser(user);
			return true;
		} else {
			return false;
		}

	}

	/*
	 * 新建工程
	 */

	public boolean createProject(Project project) {

		ProjectDaoImpl projectdao = new ProjectDaoImpl();
		// 在插入数据之前 要先判断一下工程名是否已存在
		if (projectdao.haveProjectName(project) == false) {
			projectdao.insertProject(project);
			System.out.println("插入工程成功");
			return true;
		} else {
			System.out.println("工程名已经存在");
			return false;
		}
	}

	/*
	 * 新建场景
	 * 
	 */

	public boolean createScenario(Scenario scenario) {
		// 在插入数据之前 要先判断一下场景名是否已存在
		ScenarioDao scenarioDao = new ScenarioDaoImpl();
		if (scenarioDao.haveScenarioName(scenario) == false) {
			// 创建场景时 所有节点数量为0,这里不设置会影响到后面的数量自增。
			scenario.setNumberNode(0);
			scenario.setNumberSimpleNode(0);
			scenario.setNumberComplexNode(0);
			scenarioDao.insertScenario(scenario);
			System.out.println("新建场景成功！");
			return true;
		} else {
			System.out.println("场景名已经存在！");
			return false;
		}
	}

	/*
	 * 查找当前用户下的所有工程。
	 */
	public List<Project> findAllProjectByUserId(User user) {
		ProjectDaoImpl projectDao = new ProjectDaoImpl();
		return projectDao.findAllProjectByUserId(user);

	}

	/*
	 * 查到当前工程下的所有场景
	 */
	public List<Scenario> findAllScenarioByProjectId(int p_id) {
		// TODO Auto-generated method stub
		ScenarioDao scenarioDao = new ScenarioDaoImpl();
		return scenarioDao.findAllScenarioByProjectId(p_id);
	}

	/*
	 * 查找当前场景下的所有节点
	 */
	public List<Node> findAllNodeByScenarioId(int s_id) {
		NodeDao nodeDao = new NodeDaoImpl();
		return nodeDao.findAllNodeByScenarioId(s_id);
	}

	/*
	 * 新建节点
	 */
	public boolean createNode(Node node, int s_id) {
		NodeDao nodeDao = new NodeDaoImpl();
		
		// 判断同一场景下的节点名称是否重复
		if (nodeDao.haveNodeName(node, s_id) == false) { 

			// 判断节点类型,根据类型对节点所属场景表的节点计数字段进行自增。
			if (node.getNodeType() == 2) { // 若为复杂节点(0,1均代表简单节点)
				nodeDao.plusNumberComplexNode(s_id);
			} else {
				nodeDao.plusNumberSimpleNode(s_id);
			}
			nodeDao.insertNode(node);
			return true;
		}
		return false;
	}

}
