package cn.edu.uestc.platform.pojo;


/*
 * 此类存在问题
 */
public class Internalmodule {
	private int im_id;
	private int internalModuleStatus;
	private int internalModuleCategory;
	private int hardwareArchitecture;
	private int operatingSystem;
	private int numberPort;
	private int node_id;
	private String internalModuleName;
	private String imagePath;
	public int getIm_id() {
		return im_id;
	}
	public void setIm_id(int im_id) {
		this.im_id = im_id;
	}
	public int getInternalModuleStatus() {
		return internalModuleStatus;
	}
	public void setInternalModuleStatus(int internalModuleStatus) {
		this.internalModuleStatus = internalModuleStatus;
	}
	public int getInternalModuleCategory() {
		return internalModuleCategory;
	}
	public void setInternalModuleCategory(int internalModuleCategory) {
		this.internalModuleCategory = internalModuleCategory;
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
	public int getNode_id() {
		return node_id;
	}
	public void setNode_id(int node_id) {
		this.node_id = node_id;
	}
	public String getInternalModuleName() {
		return internalModuleName;
	}
	public void setInternalModuleName(String internalModuleName) {
		this.internalModuleName = internalModuleName;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Internalmodule [im_id=" + im_id + ", internalModuleStatus=" + internalModuleStatus
				+ ", internalModuleCategory=" + internalModuleCategory + ", hardwareArchitecture="
				+ hardwareArchitecture + ", operatingSystem=" + operatingSystem + ", numberPort=" + numberPort
				+ ", node_id=" + node_id + ", internalModuleName=" + internalModuleName + ", imagePath=" + imagePath
				+ "]";
	}
	

	
}
