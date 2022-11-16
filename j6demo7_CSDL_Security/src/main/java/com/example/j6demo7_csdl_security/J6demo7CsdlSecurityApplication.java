package com.example.j6demo7_csdl_security;

import com.example.j6demo7_csdl_security.dao.AuthorityDAO;
import com.example.j6demo7_csdl_security.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class J6demo7CsdlSecurityApplication {
    @Autowired
    static
    AuthorityDAO authorityDAO;
    public static void main(String[] args) {
        SpringApplication.run(J6demo7CsdlSecurityApplication.class, args);
        List<Authority> authorities = authorityDAO.findAll();
        for (Authority a: authorities
        ) {
            System.out.println(a.getAccount().getFullname());
        }
        System.out.println("Hello");
    }

}
