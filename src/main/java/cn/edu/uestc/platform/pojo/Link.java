package cn.edu.uestc.platform.pojo;

public class Link {

	private int l_id;
	private int linkType;
	private int linkStatus;
	private int channelModel;
	private int scenario_id;
	private int txPort_id;
	private int rxPort_id;
	private String fromNodeName;
	private String toNodeName;
	private String linkName;
	private double linkLength;
	private double linkNoise;
	private double linkInterference;

	public int getL_id() {
		return l_id;
	}

	public void setL_id(int l_id) {
		this.l_id = l_id;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	public int getLinkStatus() {
		return linkStatus;
	}

	public void setLinkStatus(int linkStatus) {
		this.linkStatus = linkStatus;
	}

	public int getChannelModel() {
		return channelModel;
	}

	public void setChannelModel(int channelModel) {
		this.channelModel = channelModel;
	}

	public int getScenario_id() {
		return scenario_id;
	}

	public void setScenario_id(int scenario_id) {
		this.scenario_id = scenario_id;
	}

	public int getTxPort_id() {
		return txPort_id;
	}

	public void setTxPort_id(int txPort_id) {
		this.txPort_id = txPort_id;
	}

	public int getRxPort_id() {
		return rxPort_id;
	}

	public void setRxPort_id(int rxPort_id) {
		this.rxPort_id = rxPort_id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public double getLinkLength() {
		return linkLength;
	}

	public void setLinkLength(double linkLength) {
		this.linkLength = linkLength;
	}

	public double getLinkNoise() {
		return linkNoise;
	}

	public void setLinkNoise(double linkNoise) {
		this.linkNoise = linkNoise;
	}

	public double getLinkInterference() {
		return linkInterference;
	}

	public void setLinkInterference(double linkInterference) {
		this.linkInterference = linkInterference;
	}

	public String getFromNodeName() {
		return fromNodeName;
	}

	public void setFromNodeName(String fromNodeName) {
		this.fromNodeName = fromNodeName;
	}

	public String getToNodeName() {
		return toNodeName;
	}

	public void setToNodeName(String toNodeName) {
		this.toNodeName = toNodeName;
	}

	@Override
	public String toString() {
		return "Link [l_id=" + l_id + ", linkType=" + linkType + ", linkStatus=" + linkStatus + ", channelModel="
				+ channelModel + ", scenario_id=" + scenario_id + ", txPort_id=" + txPort_id + ", rxPort_id="
				+ rxPort_id + ", fromNodeName=" + fromNodeName + ", toNodeName=" + toNodeName + ", linkName=" + linkName
				+ ", linkLength=" + linkLength + ", linkNoise=" + linkNoise + ", linkInterference=" + linkInterference
				+ "]";
	}

}
