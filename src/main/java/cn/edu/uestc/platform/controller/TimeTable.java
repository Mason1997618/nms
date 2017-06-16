package cn.edu.uestc.platform.controller;

import java.util.List;

public class TimeTable {
	private String startTime;
	private List<Link> linkArray;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public List<Link> getLinkArray() {
		return linkArray;
	}

	public void setLinkArray(List<Link> linkArray) {
		this.linkArray = linkArray;
	}

	@Override
	public String toString() {
		return "TimeTable [startTime=" + startTime + ", linkArray=" + linkArray + "]";
	}

}
