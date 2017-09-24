package cn.edu.uestc.platform.utils;

import java.util.List;
import java.util.Set;

import cn.edu.uestc.platform.dealwithstk.NodeCreater;
import cn.edu.uestc.platform.pojo.LinkForFilter;
import cn.edu.uestc.platform.pojo.Node;
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

	/*
	 * Set转Json
	 */
	public static <T> JSONArray SetToJson(Set<T> T) {
		JSONArray json = JSONArray.fromObject(T);
		return json;
	}

	/*
	 * 字符串转Json对象
	 */
	// public static StringToJson(String str){
	//
	// JSONObject jsStr = JSONObject.
	//
	//
	// }

	/*
	 * 把JSon字符串转化为JAVA对象数组
	 */
	public static List JSonToCollection(String str,Object obj) {
		JSONArray json = JSONArray.fromObject(str);// userStr是json字符串
	
		if(obj instanceof LinkForFilter){
			List<LinkForFilter> users = (List<LinkForFilter>) JSONArray.toCollection(json, LinkForFilter.class);
			return users;
		}
		
		if(obj instanceof Node){
			List<Node> nodes = (List<Node>) JSONArray.toCollection(json, Node.class);
			return nodes;
		}
		
		if(obj instanceof NodeCreater){
			List<NodeCreater> nodes = (List<NodeCreater>) JSONArray.toCollection(json, NodeCreater.class);
			return nodes;
		}
		return json;
	}
}
