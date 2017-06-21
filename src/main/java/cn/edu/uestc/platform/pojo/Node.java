package cn.edu.uestc.platform.pojo;

import java.util.List;

public class Node {

	private int n_id;
	private String nodeName;
	private String manageIp;
	private int nodeType;
	private int hardwareArchitecture;
	private int operatingSystem;
	private int numberPort;
	private int numberInternalModule;
	private int numberInternalLink;
	private int nodeStatus;
	private String imagePath;
	private int Scenario_id;

	private List<Port> ports;

	public List<Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	@Override
	public String toString() {
		return "Node [n_id=" + n_id + ", nodeName=" + nodeName + ", manageIp=" + manageIp + ", nodeType=" + nodeType
				+ ", hardwareArchitecture=" + hardwareArchitecture + ", operatingSystem=" + operatingSystem
				+ ", numberPort=" + numberPort + ", numberInternalModule=" + numberInternalModule
				+ ", numberInternalLink=" + numberInternalLink + ", nodeStatus=" + nodeStatus + ", imagePath="
				+ imagePath + ", Scenario_id=" + Scenario_id + ", ports=" + ports + "]";
	}

	public int getN_id() {
		return n_id;
	}

	public void setN_id(int n_id) {
		this.n_id = n_id;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getManageIp() {
		return manageIp;
	}

	public void setManageIp(String manageIp) {
		this.manageIp = manageIp;
	}

	public int getNodeType() {
		return nodeType;
	}

	public void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public int getHardwareArchitecture() {
		return hardwareArchitecture;
	}

	public void setHardwareArchitecture(int hardwareArchitecture) {
		this.hardwareArchitecture = hardwareArchitecture;
	}

	public int getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(int operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public int getNumberPort() {
		return numberPort;
	}

	public void setNumberPort(int numberPort) {
		this.numberPort = numberPort;
	}

	public int getNumberInternalModule() {
		return numberInternalModule;
	}

	public void setNumberInternalModule(int numberInternalModule) {
		this.numberInternalModule = numberInternalModule;
	}

	public int getNumberInternalLink() {
		return numberInternalLink;
	}

	public void setNumberInternalLink(int numberInternalLink) {
		this.numberInternalLink = numberInternalLink;
	}

	public int getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(int nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getScenario_id() {
		return Scenario_id;
	}

	public void setScenario_id(int scenario_id) {
		Scenario_id = scenario_id;
	}

}
