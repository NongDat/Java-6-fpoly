package com.j6d1.app;

import java.io.File;
import java.io.IOException;

import javax.security.auth.Subject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson1 {
	public static void main(String[] args) throws Exception {
//		demo1();
		demo2();
	}

	private static void demo2() throws Exception {
		String path = "D:\\JAVA\\Java 6\\inteliJ_Project\\J6Demo1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		// chuyển chuỗi thành JsonNode
		JsonNode students = mapper.readTree(new File(path));
		// lấy từng Node con ra chuyển sang kiểu DL mong muốn
		students.iterator().forEachRemaining(student -> {
			System.out.println(">>Name: " + student.get("name").asText());
		});
	}

	private static void demo1() throws Exception {
		String path = "D:\\JAVA\\Java 5\\eclipse-workspace-java5\\J6Demo1\\src\\main\\resources\\student.json";
		ObjectMapper mapper = new ObjectMapper();
		// chuyển chuỗi thành JsonNode
		JsonNode student = mapper.readTree(new File(path));
		// lấy từng Node con ra chuyển sang kiểu DL mong muốn
		System.out.println(">>Name: " + student.get("name").asText());
		System.out.println(">>Marks: " + student.get("marks").asDouble());
		System.out.println(">>Gender: " + student.get("gender").asBoolean());
		System.out.println(">>Email: " + student.get("contact").get("email").asText());
		// Hàm đặc biệt đi tìm đối tượng cho dù nó sâu ở cấp độ bao nhiêu trong cái Tree của phương thức đó
		System.out.println(">>Phone: " + student.findValue("phone").asText());
		student.get("subjects").iterator().forEachRemaining(Subject -> {
			System.out.println(">>Subject: " + Subject.asText());
		});
		
		
	}
}
