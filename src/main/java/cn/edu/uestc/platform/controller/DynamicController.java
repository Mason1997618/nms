package cn.edu.uestc.platform.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/dyna")
public class DynamicController {

	@ResponseBody
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public ResponseEntity<String> SendData(String data, HttpSession session) {// 拿到数据
		System.out.println("从前端拿到的数据：" + data);
		ObjectMapper objectMapper = new ObjectMapper();
		List<TimeTable> d = null;
		try {
			d = objectMapper.readValue(data, new TypeReference<List<TimeTable>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("反序列化生成Java对象：" + d);// 将对象存储到session中，当前就zph一个用户，所以直接存储去吧
		session.setAttribute("zph", d);
		return ResponseEntity.ok("生成节点成功");
	}

	@ResponseBody
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<List<TimeTable>> getData(HttpSession session) {
		List<TimeTable> times = (List<TimeTable>) session.getAttribute("zph");
		return ResponseEntity.ok(times);
	}
}
