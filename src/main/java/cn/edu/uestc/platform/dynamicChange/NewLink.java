package cn.edu.uestc.platform.dynamicChange;

public class NewLink {

	private String from;// 起始节点
	private String end;// 终止节点
	private String startTime;// 链路起始时间-->距离2007/7/1 00:00:00 的时间-->用 s 表示
	private String range;

	public NewLink(String from, String end, String startTime, String range) {
		super();
		this.from = from;
		this.end = end;
		this.startTime = startTime;
		this.range = range;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end.hashCode() + from.hashCode();
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		NewLink other = (NewLink) obj;
		if (!startTime.equals(other.startTime))
			return false;
		if ((this.getFrom().equals(other.getFrom()) && this.getEnd().equals(other.getEnd()))
				|| (this.getFrom().equals(other.getEnd()) && this.getEnd().equals(other.getFrom()))) {
			return true;
		}
		return false;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
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
		return "NewLink [from=" + from + ", end=" + end + ", startTime=" + startTime + ", range=" + range + "]";
	}

}
