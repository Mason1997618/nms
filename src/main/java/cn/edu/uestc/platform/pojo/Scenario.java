package cn.edu.uestc.platform.pojo;

import java.util.List;

public class Scenario {
	private int s_id;
	private String scenarioName;
	private String dynamicTopologyFile;
	private int numberNode;
	private int numberSimpleNode;
	private int numberComplexNode;
	private int scenarioStatus;
	private int scenarioType;
	private int project_id;
	private List<Node> nodes;

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		this.scenarioName = scenarioName;
	}

	public String getDynamicTopologyFile() {
		return dynamicTopologyFile;
	}

	public void setDynamicTopologyFile(String dynamicTopologyFile) {
		this.dynamicTopologyFile = dynamicTopologyFile;
	}

	public int getNumberNode() {
		return numberNode;
	}

	public void setNumberNode(int numberNode) {
		this.numberNode = numberNode;
	}

	public int getNumberSimpleNode() {
		return numberSimpleNode;
	}

	public void setNumberSimpleNode(int numberSimpleNode) {
		this.numberSimpleNode = numberSimpleNode;
	}

	public int getNumberComplexNode() {
		return numberComplexNode;
	}

	public void setNumberComplexNode(int numberComplexNode) {
		this.numberComplexNode = numberComplexNode;
	}

	public int getScenarioStatus() {
		return scenarioStatus;
	}

	public void setScenarioStatus(int scenarioStatus) {
		this.scenarioStatus = scenarioStatus;
	}

	public int getScenarioType() {
		return scenarioType;
	}

	public void setScenarioType(int scenarioType) {
		this.scenarioType = scenarioType;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	@Override
	public String toString() {
		return "Scenario [s_id=" + s_id + ", scenarioName=" + scenarioName + ", dynamicTopologyFile="
				+ dynamicTopologyFile + ", numberNode=" + numberNode + ", numberSimpleNode=" + numberSimpleNode
				+ ", numberComplexNode=" + numberComplexNode + ", scenarioStatus=" + scenarioStatus + ", scenarioType="
				+ scenarioType + ", project_id=" + project_id + ", nodes=" + nodes + "]";
	}

	

}
