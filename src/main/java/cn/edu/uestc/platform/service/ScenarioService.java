package cn.edu.uestc.platform.service;

import java.util.List;

import cn.edu.uestc.platform.dao.ScenarioDao;
import cn.edu.uestc.platform.dao.ScenarioDaoImpl;
import cn.edu.uestc.platform.pojo.Scenario;

public class ScenarioService {
	
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
	 * 查到当前工程下的所有场景
	 */
	public List<Scenario> findAllScenarioByProjectId(int p_id) {
		// TODO Auto-generated method stub
		ScenarioDao scenarioDao = new ScenarioDaoImpl();
		return scenarioDao.findAllScenarioByProjectId(p_id);
	}
}
