package com.example.ex;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter					// get()
@Setter					// set()
@NoArgsConstructor		// 매개변수가 없는, ()괄호가 비어있는 생성자 -> 기본 생성자
@AllArgsConstructor		// 모든 매개변수가 있는 생성자
public class User {
	
	private String name;	// 이름
	private int age;		// 나이
}