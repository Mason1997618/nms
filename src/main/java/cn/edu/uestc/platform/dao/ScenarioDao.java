package cn.edu.uestc.platform.dao;

import java.util.List;

import cn.edu.uestc.platform.pojo.Scenario;

public interface ScenarioDao {
	
	
	public boolean haveScenarioName(Scenario scenario);
	
	public boolean insertScenario(Scenario scenario);
	
	public Scenario findByScenarioName(Scenario scenario);

	public List<Scenario> findAllScenarioByProjectId(int s_id);

	public void deletescenario(int s_id);

	public void updateScenarioStatusToDown(int s_id);
	
	public void updateScenarioStatusToUp(int s_id);


}
