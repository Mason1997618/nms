package cn.edu.uestc.platform.test.newTest;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.edu.uestc.platform.dynamicChange.DynamicFactory;
import cn.edu.uestc.platform.dynamicChange.Filters;
import cn.edu.uestc.platform.dynamicChange.IPCreateFactory;
import cn.edu.uestc.platform.dynamicChange.NewLink;

public class Gra44Test {

	@Test
	public void fun(){
//		for(NewLink link :Gra44Filters.readMinuteMEORL("0")){
//			System.out.println(link);
//		}
		for(NewLink link :Filters.readMinuteMEORL("0")){
			System.out.println(link);
		}
	}
	
	@Test
	public void fun1(){
//		for(NewLink link :Gra44Filters.readMinuteMEORL("0")){
//			System.out.println(link);
//		}
//		for(NewLink link :Filters.readMinuteMEOToGEO("0")){
//			System.out.println(link);
//		}
		for(int i=0;i<20;i++){
			System.out.println(Filters.readMinutePToLEO(i+""));
		}
		
	}
	
	@Test
	public void fun3(){
		for (String[] link : IPCreateFactory.createTablePToLEO()) {
			System.out.println(link[0]+" "+link[2]);
			System.out.println(link[1]+" "+link[3]);
//			DynamicFactory.addPort(os, link[0], link[2]);
//			TimeUnit.SECONDS.sleep(2);
////			DynamicFactory.addPort(os, link[1], link[3]);
//			TimeUnit.SECONDS.sleep(2);
		}
	}
}
