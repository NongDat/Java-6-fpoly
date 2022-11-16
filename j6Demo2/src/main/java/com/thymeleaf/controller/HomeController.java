package com.thymeleaf.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thymeleaf.bean.Student;

@Controller
public class HomeController {
	@RequestMapping("/home/index")
	public String index(Model model) throws Exception {
		model.addAttribute("message", "Welcome to Thymeleaf");
		
		ObjectMapper mapper = new ObjectMapper();
		String path = "D:\\JAVA\\Java 5\\eclipse-workspace-java5\\j6Demo2\\src\\main\\resources\\static\\student.json";
		Student student = mapper.readValue(new File(path), Student.class);
		model.addAttribute("sv", student);
		return "home/index";
	}
}
