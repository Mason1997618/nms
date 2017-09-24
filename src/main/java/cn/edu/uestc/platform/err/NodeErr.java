package cn.edu.uestc.platform.err;

public class NodeErr {
	
	private String nodeNameErr;
	private String ipErr;
	
	public NodeErr(String nameErr,String ipErr){
		this.nodeNameErr = "节点名字已存在";
		this.ipErr = "IP地址已存在";
	}

	public String getNodeNameErr() {
		return nodeNameErr;
	}

	public void setNodeNameErr(String nodeNameErr) {
		this.nodeNameErr = nodeNameErr;
	}

	public String getIpErr() {
		return ipErr;
	}

	public void setIpErr(String ipErr) {
		this.ipErr = ipErr;
	}

	
}
