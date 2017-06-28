package cn.edu.uestc.platform.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSoneUtils {
	/*
	 * 集合转JSone
	 */
	public static <T> JSONArray ListToJson(List<T> T) {
		JSONArray json = JSONArray.fromObject(T);
		return json;
	}

	/*
	 * 对象转JSone
	 */
	public static <T> JSONObject ObjToJson(T obj) {
		JSONObject json = JSONObject.fromObject(obj);
		return json;
	}
}
