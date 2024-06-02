package com.example.ex;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lombok")
@Controller
public class LombokController {		// User01.java 클래스와 함께 보시오.

	@GetMapping("/lom01")
	public String lom01(Model model) {
		Lombok lombok = new Lombok();
		lombok.setName("박주완");
		lombok.setAge(23);
		lombok.setTest("공부한다.");
		
		model.addAttribute("lombok", lombok);
		return "lom01";
	}
	
	@GetMapping("/lom02")
	public String lom02(Model model) {
//		@NoArgsConstructor 설정에 의해 기본생성자 사용 가능
		Lombok lombok = new Lombok();
		lombok.setName("성민형");
		lombok.setAge(25);
		
//		@NonNull 설정에 의해 null 대입 시 소스코드에는 오류가 없지만, 실행 시 NullPointException 예외 발생
//		user.setTest(null);
		
		lombok.setTest("lombok");
		model.addAttribute("lombok", lombok);
		return "lom01";
	}
	
	@GetMapping("/lom03")
	public String lom03(Model model) {
//		@AllArgsConstructor 설정에 의해 모든 변수를 매개변수로 받는 생성자
		Lombok lombok = new Lombok("이대용", 30, "hello");
		
		model.addAttribute("lombok", lombok);
		return "lom03";
	}
	
	@GetMapping("/lom04")
	public String lom04(Model model) {
//		@Builder 생성자를 사용하지 않고, 빌더 패턴을 자동으로 생성했다.
		Lombok lombok = Lombok.builder().name("민지썜").age(100).test("hi").build();
		
		model.addAttribute("lombok", lombok);
		return "lom04";
	}
}