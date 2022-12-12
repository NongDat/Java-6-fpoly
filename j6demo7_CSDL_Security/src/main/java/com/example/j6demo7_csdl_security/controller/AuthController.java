package com.example.j6demo7_csdl_security.controller;

import com.example.j6demo7_csdl_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @Autowired
    UserService userService;
    @RequestMapping("/auth/login/form")
    public String form() {

        return "auth/login";
    }

    //     sau khi login success trở lại trang form để hiển thị thông báo
    @RequestMapping("/auth/login/success")
    public String success(Model model) {
        model.addAttribute("msg", "Login Success");
        return "forward:/auth/login/form";
    }

    //     sau khi login fail trở lại trang form để hiển thị thông báo lỗi
    @RequestMapping("/auth/login/error")
    public String error(Model model) {
        model.addAttribute("msg", "Login fail");
        return "forward:/auth/login/form";
    }

    //     sau khi logout success trở lại trang form để hiển thị thông báo đăng xuất thành công
    @RequestMapping("/auth/logoff/success")
    public String logoff(Model model) {
        model.addAttribute("msg", "Logout success");
        return "forward:/auth/login/form";
    }

    @RequestMapping("/auth/access/denied")
    public String denied(Model model) {
        model.addAttribute("msg", "Bạn không có quyền truy xuất!");
        return "auth/login";
    }

    @RequestMapping("/oauth2/login/success")
    public String success(OAuth2AuthenticationToken oauth2){
        userService.loginFrom(oauth2);
        return "forward:/auth/login/success";
    }
}
