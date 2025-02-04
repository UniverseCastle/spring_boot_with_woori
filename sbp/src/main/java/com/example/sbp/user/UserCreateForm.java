package com.example.sbp.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 이름은 필수 항목입니다.")
	private String username;
	
	@NotEmpty(message = "비밀번호는 필수 항목입니다.")
	private String pw1;
	
	@NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
	private String pw2;
	
	@Email
	@NotEmpty(message = "이메일은 필수 항목입니다.")
	private String email;
	
}