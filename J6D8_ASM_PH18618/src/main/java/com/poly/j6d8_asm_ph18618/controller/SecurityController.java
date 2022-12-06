package com.poly.j6d8_asm_ph18618.controller;

import com.poly.j6d8_asm_ph18618.dao.AccountDAO;
import com.poly.j6d8_asm_ph18618.entity.Account;
import com.poly.j6d8_asm_ph18618.entity.Authority;
import com.poly.j6d8_asm_ph18618.entity.Role;
import com.poly.j6d8_asm_ph18618.service.AccountService;
import com.poly.j6d8_asm_ph18618.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class SecurityController {
    @Autowired
    AccountService accountService;
    @Autowired
    AuthorityService authorityService;
    @Autowired
    Authority authority;
    @RequestMapping("/security/login/form")
    public String loginForm(Model model) {
        model.addAttribute("message", "Vui lòng đăng nhập");
        return "security/login";
    }

    @RequestMapping("/security/login/success")
    public String loginSuccess(Model model) {
        model.addAttribute("message", "Đăng nhập thành công!");
        return "redirect:/product/list";
    }

    @RequestMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("message", "Sai thông tin đăng nhập!");
        return "security/login";
    }

    @RequestMapping("/security/unauthoried")
    public String unauthoried(Model model) {
        model.addAttribute("message", "Không có quyền truy xuất!");
        return "security/login";
    }

    @RequestMapping("/security/logoff/success")
    public String logoffSuccess(Model model) {
        model.addAttribute("message", "Bạn đã đăng xuất!");
        return "security/login";
    }

    @RequestMapping("/security/register-form")
    public String registerForm(Model model) {
        model.addAttribute("account", new Account());
        return "security/register";
    }

    @PostMapping("/security/register")
    public String register(
            Model model,
            @RequestParam("rePass") String rePass,
            @Validated @ModelAttribute("account")Account account,
            Errors errors
    ) {

        System.out.println("test"+rePass);
        if(errors.hasErrors()) {
            model.addAttribute("message", " Vui lòng sửa các lỗi sau");
            return "security/register";
        }
        if(!account.getPassword().equals(rePass)) {
            model.addAttribute("rePass", "Please enter your RePassword");
            return "security/register";
        }
        List<Account> list = accountService.findAll();
        for (Account a: list) {
            if(a.getUsername().equals(account.getUsername())) {
                model.addAttribute("account", account);
                model.addAttribute("message", " Tài khoản đã tồn tại!");
                return "security/register";
            }
        }

        accountService.save(account);
        Role role = new Role();
        role.setId("CUST");
        role.setName("CUSTOMERS");
        authority.setRole(role);
        authority.setAccount(account);
        authorityService.create(authority);

        return "redirect:/security/login/form";
    }

    @GetMapping("/security/account/form/{id}")
    public String editAccount(
            @PathVariable("id") String id,
            Model model
    ) {
        Account account = accountService.findById(id);
        model.addAttribute("account", account);
        return "/security/account";
    }

    @PostMapping("/security/account/update")
    public String updateAccount(
            Model model,
            @Validated @ModelAttribute("account") Account account,
            Errors errors
    ) {
        if (errors.hasErrors()) {

            return "/security/account";
        }
        accountService.save(account);

        return "redirect:/product/list ";
    }
}
