package cn.edu.uestc.platform.winter.pojo;

/**
 * A->B B->A 属于同一条链路，用于Set集合判断 两个点是否属于同一链路，重写了 HashCode 和 equals 方法
 */
public class RelativeLink {

	private String nodeA;
	private String nodeB;

	public RelativeLink(String nodeA, String nodeB) {
		super();
		this.nodeA = nodeA;
		this.nodeB = nodeB;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nodeA.hashCode() + nodeB.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		RelativeLink other = (RelativeLink) obj;
		// 此处！
		if ((this.getNodeA().equals(other.getNodeB()) && this.getNodeB().equals(other.getNodeA()))
				|| (this.getNodeA().equals(other.getNodeA()) && this.getNodeB().equals(other.getNodeB()))) {
			return true;
		}
		return false;
	}

	public String getNodeA() {
		return nodeA;
	}

	public void setNodeA(String nodeA) {
		this.nodeA = nodeA;
	}

	public String getNodeB() {
		return nodeB;
	}

	public void setNodeB(String nodeB) {
		this.nodeB = nodeB;
	}

	@Override
	public String toString() {
		return "RealtiveLink [nodeA=" + nodeA + ", nodeB=" + nodeB + "]";
	}

}
