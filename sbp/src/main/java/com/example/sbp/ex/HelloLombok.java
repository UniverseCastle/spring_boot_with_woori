package com.example.sbp.ex;

//import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@Setter
@Getter
@RequiredArgsConstructor		// setter 필요 없음. 생성자에 직접 set 해줌. final 선언해줘야함
public class HelloLombok {
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok hl = new HelloLombok("안녕?", 200);		// 여기에서 set
//		hl.setHello("헬로");
//		hl.setLombok(100);
		
		System.out.println(hl.getHello());
		System.out.println(hl.getLombok());
	}
}