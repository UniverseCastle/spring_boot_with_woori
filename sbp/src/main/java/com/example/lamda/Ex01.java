package com.example.lamda;
/*
	람다식 (익명함수)
	(타입 매개변수) -> {실행문;}
	(int a) -> {System.out.println(a);}
	() -> System.out.println(a);	매개변수가 없는 경우 비워도됨 / 타입 생략 가능
	(x, y) -> {return x+y;}			
	(x, y) -> return x+y;
*/

import groovyjarjarantlr4.v4.parse.ANTLRParser.block_return;

interface Test1 {
//	매개변수가 없는 추상 메서드
	public abstract void name();
}
interface Test2 {
//	매개변수가 하나인 추상 메서드
	public abstract void name(String name);
}
interface Test3 {
//	두 개의 매개변수를 가진 추상 메서드
	public abstract void name(String name, int age);
}
interface Test4 {
//	정수를 리턴하는 추상 메서드
	public abstract int sum(int a, int b);
}

public class Ex01 {
	public static void main(String[] args) {
		
//		익명클래스로 Test1 인터페이스 구현
		Test1 t = new Test1() {
//		name 메서드 구현
			public void name() {
				System.out.println("김자바");
			}
		};
		t.name();
		
//		람다식으로 Test1 인터페이스 구현
		Test1 t1 = () -> {
			System.out.println("이자바");
		};
		t1.name();
		
//		람다식으로 Test2 인터페이스 구현 - 매개변수 1개
		Test2 t2 = n -> {
			System.out.println(n);
		};
		t2.name("박자바");
		
//		람다식으로 Test3 인터페이스 구현 - 타입이 다른 2개
		Test3 t3 = (n, a) -> {
			for(int i=0; i<=a; i++) {
				System.out.println(n);
			}
		};
		t3.name("최자바", 3);
		
//		람다식으로 Test4 인터페이스 구현 - 리턴타입 있는 메서드
		Test4 t4 = (a, b) -> {
			return a+b;
		};
		
//		메서드 호출
		System.out.println(t4.sum(2, 3));
		
//		람다식으로 Test4 인터페이스 구현 (간결한 버전)
		Test4 t5 = (a, b) -> a + b;
//		메서드 호출
		System.out.println(t5.sum(2, 3));
	}
}