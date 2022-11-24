//package com.example.j6demo7_csdl_security.controller;
//
//import com.example.j6demo7_csdl_security.dao.AccountDAO;
//import com.example.j6demo7_csdl_security.dao.AuthorityDAO;
//import com.example.j6demo7_csdl_security.dao.RoleDAO;
//import com.example.j6demo7_csdl_security.entity.Account;
//import com.example.j6demo7_csdl_security.entity.Authority;
//import com.example.j6demo7_csdl_security.entity.Role;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class HomeController {
//    @Autowired
//    AuthorityDAO authorityDAO;
//    @Autowired
//    RoleDAO roleDAO;
//    @Autowired
//    AccountDAO accountDAO;
//    @GetMapping("/home")
//    public String test() {
//        List<Authority> authorities = authorityDAO.findAll();
//        List<Account> accounts = accountDAO.findAll();
//        List<Role> roles = roleDAO.findAll();
//        for (Authority a: authorities  ) {
//            System.out.println(a.getAccount().getFullname());
//        }
//        for (Account a: accounts  ) {
//            System.out.println(a.getFullname());
//        }
//        for (Role a: roles  ) {
//            System.out.println(a.getName());
//        }
//        return "HOME";
//    }
//}
