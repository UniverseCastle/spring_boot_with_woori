package com.example.param;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String id;
	private String pw;
	private String email;
	private String gender;
	private String country;
	private List<String> hobbies;
}