package cn.edu.uestc.platform.utils;

import java.util.List;

import net.sf.json.JSONArray;

public class JSoneUtils {

	
	public static <T> JSONArray ListToJson(List<T> T){
		JSONArray json = JSONArray.fromObject(T);	
		return json;
	}
}
