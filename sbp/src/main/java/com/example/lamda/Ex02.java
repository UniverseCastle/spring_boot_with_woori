package com.example.lamda;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Ex02 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("aaa", "bbb", "ccc");
		
//		Iterator 외부 반복자
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			String abc = iter.next();
			System.out.println(abc);
		}
		
//		또다른 List
		List<String> list2 = Arrays.asList("100", "200", "300");
//		java.util.stream 내부 반복자
		Stream<String> stream = list2.stream();
//		stream 사용하여 값을 꺼내줌
		stream.forEach(ott -> System.out.println(ott));
	}
}