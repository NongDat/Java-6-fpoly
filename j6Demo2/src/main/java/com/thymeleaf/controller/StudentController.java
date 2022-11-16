package com.thymeleaf.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thymeleaf.bean.Student1;

@Controller
public class StudentController {
	
	@RequestMapping("/student")
	public String index(
		Model model,
		@RequestParam("index") Optional<Integer> index
	) throws Exception {
		/**
		 * Đọc danh sách sinh viên từ file
		 */
		ObjectMapper mapper = new ObjectMapper();
		File path = ResourceUtils.getFile("classpath:com/thymeleaf/bean/students.json");
		TypeReference<List<Student1>> typeref = new TypeReference<>() {};
		List<Student1> students = mapper.readValue(path, typeref);
		
		int i = index.orElse(0); // k truyển thì lấy vị trí 0
		model.addAttribute("n", i);
		model.addAttribute("sv", students.get(i));
		return "scope/student";
	}
}
