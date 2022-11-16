package com.j6d1.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.j6d1.bean.Student;

public class StreamAPI {
	static List<Student> list = Arrays.asList(
			new Student("Nguyễn Văn Tèo", true, 7.5),
			new Student("Nguyễn Văn An", true, 6.5),
			new Student("Phạm Đức Cường", true, 4.5),
			new Student("Nông Văn Đạt", true, 9.5),
			new Student("Đoàn Thị Kim Anh", true, 8.5)
			);
public static void main(String[] args) {
//	demo1();
//	demo2();
//	demo3();
	demo4();
}

private static void demo4() {

	double average = list.stream()
			.mapToDouble(sv -> sv.getMarks())
			.average().getAsDouble();
	System.out.println("mark average: " + average);
//	 sum
	double sum = list.stream()
			.mapToDouble(sv -> sv.getMarks())
			.sum();
	System.out.println("mark total: " + sum);
	
//	 min
	double min_mark = list.stream()
			.mapToDouble(sv -> sv.getMarks())
			.min().getAsDouble();
	System.out.println("mark min: "+ min_mark);
	
//	 all Match
	boolean all_passed = list.stream().allMatch(sv -> sv.getMarks() > 5);
	System.out.println("all_passed: "+ all_passed);
	
//	 all Match
	Student min_sv = list.stream()
			.reduce(list.get(0), (min, sv) -> sv.getMarks() < min.getMarks()? sv: min);	
	System.out.println("min_sv: "+ min_sv);
}

private static void demo3() {
	List<Student> result = list.stream()
			.filter(sv -> sv.getMarks() >= 6)
			.peek(sv -> System.out.println(sv.getName().toUpperCase()))
			.collect(Collectors.toList());
	
	result.forEach(sv -> {
		System.out.println(">>Name: " + sv.getName());
		System.out.println(">>Marks: " + sv.getMarks());
		System.out.println();
	});
	
}

private static void demo2() {
	List<Integer> list = Arrays.asList(1, 9, 4, 7, 2, 144);
	List<Double> result = list.stream()  //Stream <Integer>
				.filter(n -> n%2 == 0) // Stream <Integer>
				.map(n -> Math.sqrt(n)) // stream<Double>
				.peek(p -> System.out.println(p)) // stream<Double>
				.collect(Collectors.toList()); // List<Double>
}

private static void demo1() {
	Stream<Integer> stream1 = Stream.of(1, 9, 4, 7, 2);
	stream1.forEach(n -> {
		System.out.println(n);
	});
	System.out.println("stream \n");
	List<Integer> list = Arrays.asList(1, 9, 4, 7, 2);
	list.stream().forEach(n -> {
		System.out.println(n);
	});
}
		
}
