package com.j6d1.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.j6d1.bean.Student;

public class Lambda {
	static List<Student> list = Arrays.asList(
			new Student("Nguyễn Văn Tèo", true, 7.5),
			new Student("Nguyễn Văn An", true, 7.5),
			new Student("Phạm Đức Cường", true, 7.5),
			new Student("Nông Văn Đạt", true, 9.5),
			new Student("Đoàn Thị Kim Anh", true, 8.5)
			);
	
	public static void main(String[] args) {
//		demo1();
//		demo2();
//		demo3();
		demo4();
	}

	private static void demo4() {
		Demo4inter o = x -> System.out.println(x);
		o.m1(2021);
		
	}

	private static void demo3() {
		System.out.println("Demo 3\n");
		// dấu trừ là giảm : -sv1
		// không dấu trừ là tăng : sv1
			Collections.sort(list, (sv1, sv2) -> -sv1.getMarks().compareTo(sv2.getMarks()));
			list.forEach(sv -> {
				System.out.println(">> Name: " + sv.getName());
				System.out.println(">> Marks: " + sv.getMarks());
				System.out.println();
			});
	}

	private static void demo2() {
		
		list.forEach(sv ->{
			System.out.println(">> Name: " + sv.getName());
			System.out.println(">> Marks: " + sv.getMarks());
			System.out.println();
		});
		
	}

	private static void demo1() {
		List<Integer> list = Arrays.asList(1, 9, 4, 2, 8);
		list.forEach(n -> System.out.println(n));
		
	}
	
	@FunctionalInterface
	interface Demo4inter{
		void m1(int x);
		default void m2() {}
		public static void m3() {}
	}
}
