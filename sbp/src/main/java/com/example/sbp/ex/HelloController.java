package com.example.sbp.ex;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
//	실행 주소 http://localhost:8080/hello
	
	@GetMapping("/hello")			// get = 주소창에 노출, post = 숨겨서 보냄
	@ResponseBody
	public String hello() {
		return "Hello SpringBoot!! t('-'t)";
	}
}