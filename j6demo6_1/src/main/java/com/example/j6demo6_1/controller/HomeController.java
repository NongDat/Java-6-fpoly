package com.example.j6demo6_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * AuthConfig: cấu hình
 * AuthConfig_Anotation: bằng anotation và lập trình
 */
@Controller
public class HomeController {
    @Autowired
    HttpServletRequest req;
    @RequestMapping("/home/index")
    public String index(Model model) {
        model.addAttribute("msg", "This is home page");
        return "home/index";
    }

    @RequestMapping("/home/about")
    public String about(Model model) {
        model.addAttribute("msg", "This is introduce page");
        return "home/index";
    }

//    @PreAuthorize("hasRole('ADMIN')") // anotation security
    @RequestMapping("/home/admins")
    public String admins(Model model) {
        //        Cấu hình bằng lập trình
        if(!req.isUserInRole("ADMIN")) {
            return "redirect:/auth/access/denied";
        }
        model.addAttribute("msg", "Hello Adminstrator");
        return "home/index";
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")// anotation security
    @RequestMapping("/home/users")
    public String users(Model model) {
        //        Cấu hình bằng lập trình
        if(!(req.isUserInRole("ADMIN") || req.isUserInRole("USER"))) {
            return "redirect:/auth/access/denied";
        }
        model.addAttribute("msg", "Hello staff");
        return "home/index";
    }

//    @PreAuthorize("isAuthenticated()")// anotation security
    @RequestMapping("/home/authenticated")
    public String authenticated(Model model) {
        //        Cấu hình bằng lập trình
        if(req.getRemoteUser() == null) { // chưa đăng nhập
            return "redirect:/auth/login/form";
        }
        model.addAttribute("msg", "Hello authenticated user");
        return "home/index";
    }

    @RequestMapping("/home/thymeleaf1")
    public String thymeleaf1(Model model) {
        model.addAttribute("msg", "Thymeleaf - Without Extras");
        return "home/thymeleaf1";
    }

    @RequestMapping("/home/thymeleaf2")
    public String thymeleaf2(Model model) {
        model.addAttribute("msg", "Thymeleaf - With Extras");
        return "home/thymeleaf2";
    }
}
