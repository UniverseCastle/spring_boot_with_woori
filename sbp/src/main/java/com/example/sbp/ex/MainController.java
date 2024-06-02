package com.example.sbp.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@GetMapping("/sbp")
	@ResponseBody
	public String index() {
		return "<h1><font color='gray'>안녕하세요 sbp에 오신것을 환영합니다!</font></h1>";
	}
}