package cn.edu.uestc.platform.pojo;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class BigClassForFilter {

	private String fromBigNodeName;
	private Set<String> toBigNodeNames = new HashSet<>();
	private Set<String> innerNodeName = new HashSet<>();
	
	@Test
	public void demo1(){
		 String str="ABC_001";
		  if(str.indexOf("ABC")!=-1){
		   System.out.println("包含");
		  }else{
		   System.out.println("不包含");
		  }
	}

	public String getFromBigNodeName() {
		return fromBigNodeName;
	}

	public void setFromBigNodeName(String fromBigNodeName) {
		this.fromBigNodeName = fromBigNodeName;
	}

	public Set<String> getToBigNodeNames() {
		return toBigNodeNames;
	}

	public void setToBigNodeNames(Set<String> toBigNodeNames) {
		this.toBigNodeNames = toBigNodeNames;
	}

	public Set<String> getInnerNodeName() {
		return innerNodeName;
	}

	public void setInnerNodeName(Set<String> innerNodeName) {
		this.innerNodeName = innerNodeName;
	}

	@Override
	public String toString() {
		return "BigClassForFilter [fromBigNodeName=" + fromBigNodeName + ", toBigNodeNames=" + toBigNodeNames
				+ ", innerNodeName=" + innerNodeName + "]";
	}

}
