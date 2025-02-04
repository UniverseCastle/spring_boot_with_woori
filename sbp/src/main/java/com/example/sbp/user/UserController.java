package com.example.sbp.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		if (!userCreateForm.getPw1().equals(userCreateForm.getPw2())) {
			bindingResult.rejectValue("pw2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");
			
			return "signup_form";
		}
		
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getPw1(), userCreateForm.getEmail());
		} catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
			return "signup_form";
		} catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/question/list";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login_form";
	}
}