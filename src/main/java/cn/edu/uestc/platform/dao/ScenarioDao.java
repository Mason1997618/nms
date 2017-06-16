package cn.edu.uestc.platform.dao;

import cn.edu.uestc.platform.pojo.Scenario;

public interface ScenarioDao {
	
	
	public boolean haveScenarioName(Scenario scenario);
	
	public boolean insertScenario(Scenario scenario);
	
	public Scenario findByScenarioName(Scenario scenario);

}
