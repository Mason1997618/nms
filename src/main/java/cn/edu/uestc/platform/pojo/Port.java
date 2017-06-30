package cn.edu.uestc.platform.pojo;

public class Port {

	private int pt_id;
	private int antennaType;
	private int portType;
	private int portStatus;
	private int modulationScheme;
	private int channelCodingScheme;
	private int n_id;
	private String portName;
	private double antennaGain;
	private double txPower;
	private double frequencyBandwidth;
	private double txBitRate;
	private double txPacketLoss;
	private String portIp;
	public String getPortIp() {
		return portIp;
	}

	public void setPortIp(String portIp) {
		this.portIp = portIp;
	}

	//因为一个port只对应一条链路，所以这里存的是链路对象而不是链路集合。
	private Link link;

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public int getPt_id() {
		return pt_id;
	}

	public void setPt_id(int pt_id) {
		this.pt_id = pt_id;
	}

	public int getAntennaType() {
		return antennaType;
	}

	public void setAntennaType(int antennaType) {
		this.antennaType = antennaType;
	}

	public int getPortType() {
		return portType;
	}

	public void setPortType(int portType) {
		this.portType = portType;
	}

	public int getPortStatus() {
		return portStatus;
	}

	public void setPortStatus(int portStatus) {
		this.portStatus = portStatus;
	}

	public int getModulationScheme() {
		return modulationScheme;
	}

	public void setModulationScheme(int modulationScheme) {
		this.modulationScheme = modulationScheme;
	}

	public int getChannelCodingScheme() {
		return channelCodingScheme;
	}

	public void setChannelCodingScheme(int channelCodingScheme) {
		this.channelCodingScheme = channelCodingScheme;
	}

	

	public int getN_id() {
		return n_id;
	}

	public void setN_id(int n_id) {
		this.n_id = n_id;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

	public double getAntennaGain() {
		return antennaGain;
	}

	public void setAntennaGain(double antennaGain) {
		this.antennaGain = antennaGain;
	}

	public double getTxPower() {
		return txPower;
	}

	public void setTxPower(double txPower) {
		this.txPower = txPower;
	}

	public double getFrequencyBandwidth() {
		return frequencyBandwidth;
	}

	public void setFrequencyBandwidth(double frequencyBandwidth) {
		this.frequencyBandwidth = frequencyBandwidth;
	}

	public double getTxBitRate() {
		return txBitRate;
	}

	public void setTxBitRate(double txBitRate) {
		this.txBitRate = txBitRate;
	}

	public double getTxPacketLoss() {
		return txPacketLoss;
	}

	public void setTxPacketLoss(double txPacketLoss) {
		this.txPacketLoss = txPacketLoss;
	}

	@Override
	public String toString() {
		return "Port [pt_id=" + pt_id + ", antennaType=" + antennaType + ", portType=" + portType + ", portStatus="
				+ portStatus + ", modulationScheme=" + modulationScheme + ", channelCodingScheme=" + channelCodingScheme
				+ ", node_id=" + n_id + ", portName=" + portName + ", antennaGain=" + antennaGain + ", txPower="
				+ txPower + ", frequencyBandwidth=" + frequencyBandwidth + ", txBitRate=" + txBitRate
				+ ", txPacketLoss=" + txPacketLoss + ", portIp=" + portIp + ", link=" + link + "]";
	}

	

	
}
