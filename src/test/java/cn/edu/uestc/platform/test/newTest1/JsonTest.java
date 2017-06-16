package cn.edu.uestc.platform.test.newTest1;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.uestc.platform.controller.TimeTable;

public class JsonTest {

	@Test
	public void fun() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "[{\"startTime\":\"1\",\"endTime\":\"2\",\"linkArray\":[{\"fromNode\":\"node1\",\"endNode\":\"node5\",\"status\":\"1\"},{\"fromNode\":\"node9\",\"endNode\":\"node12\",\"status\":\"1\"},{\"fromNode\":\"node2\",\"endNode\":\"node6\",\"status\":\"1\"}]},{\"startTime\":\"3\",\"endTime\":\"4\",\"linkArray\":[{\"fromNode\":\"node3\",\"endNode\":\"node7\",\"status\":\"1\"},{\"fromNode\":\"node10\",\"endNode\":\"node13\",\"status\":\"1\"},{\"fromNode\":\"node4\",\"endNode\":\"node8\",\"status\":\"1\"},{\"fromNode\":\"node11\",\"endNode\":\"node14\",\"status\":\"1\"}]}]";
		List<TimeTable> d = objectMapper.readValue(jsonString, new TypeReference<List<TimeTable>>() {
		});
		System.out.println(d);
	}
}
