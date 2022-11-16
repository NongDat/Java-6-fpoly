package com.thymeleaf.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScopeController {
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping("/scope")
	public String index(Model model) {
		model.addAttribute("a", "I am in Model");
		req.setAttribute("b", "I am in Request Scope");
//		Lưu những dữ liệu cho từng phiên làm việc và dùng chung cho nhiều request khác
//		nhau của phiên đó => dùng để chia sẻ dữ liệu cho từng người khác nhau
		session.setAttribute("c", "I am in Session Scope");
//		Là nơi chia sẽ dữ liệu cho nhiều người trong toàn bộ ứng dụng
		servletContext.setAttribute("d", "I am in Application Scope");
		return "scope/index";
	}
}
