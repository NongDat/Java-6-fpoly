package com.example.j6demo7_csdl_security.controller;

import com.example.j6demo7_csdl_security.dao.AccountDAO;
import com.example.j6demo7_csdl_security.dao.AuthorityDAO;
import com.example.j6demo7_csdl_security.dao.RoleDAO;
import com.example.j6demo7_csdl_security.entity.Account;
import com.example.j6demo7_csdl_security.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AuthorityRestController {
    @Autowired
    AuthorityDAO authorityDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    AccountDAO accountDAO;

    @GetMapping("/rest/authorities")
    public Map<String, Object> getAuthorities() {
        Map<String, Object> data = new HashMap<>();
        data.put("authorities", authorityDAO.findAll());
        data.put("roles", roleDAO.findAll());
        data.put("accounts", accountDAO.findAll());

        return data;
    }
}
