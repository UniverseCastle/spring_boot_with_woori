package com.example.ex;

import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lombok {
	private String name;
	private int age;
	
	@NonNull
	private String test;
}

/*
	@Getter					: get() 메서드 자동으로 생성
	@Setter					: set() 메서드 자동으로 생성
	@ToString				: 객체의 toString() 메서드를 자동으로 생성
	@EqualsAndHashCode		: 객체의 equals(), hashCode() 메서드를 자동으로 생성
	@NoArgsConstructor		: 매개변수가 없는 기본 생성자를 생성
	@AllArgsConstructor		: 모든 변수를 매개변수로 받는 생성자를 생성
	@RequiredArgsConstructor: 클래스에 대한 매개변수가 있는 생성자를 생성
							  final, @NonNull 로 표시된 변수만 매개변수로 사용한다.
	@Builder				: 빌더패턴을 자동으로 생성
	@Data					: @Getter, @Setter, @ToString, @EqualsAndHashCode,
							  @RequiredArgsConstructor 한꺼번에 사용
*/