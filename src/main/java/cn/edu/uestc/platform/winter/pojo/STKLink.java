package cn.edu.uestc.platform.winter.pojo;

/**
 * 链路信息:用于动态链路的判断
 */
public class STKLink {
	private String fromNode;// 起始节点
	private String endNode;// 终止节点
	private String startTime;// 链路起始时间-->距离2007/7/1 00:00:00 的时间-->用 s 表示
	private String range;// 两点之间的距离

	public STKLink(String fromNode, String endNode, String startTime, String range) {
		super();
		this.fromNode = fromNode;
		this.endNode = endNode;
		this.startTime = startTime;
		this.range = range;
	}

	public String getFromNode() {
		return fromNode;
	}

	public void setFromNode(String fromNode) {
		this.fromNode = fromNode;
	}

	public String getEndNode() {
		return endNode;
	}

	public void setEndNode(String endNode) {
		this.endNode = endNode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return "STKLink [fromNode=" + fromNode + ", endNode=" + endNode + ", startTime=" + startTime + ", range="
				+ range + "]";
	}

}
