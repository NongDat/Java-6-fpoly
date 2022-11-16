package com.example.j6demo5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class heartController {
    @RequestMapping("/home/heart")
    public String get() {
        return "heart";
    }
}
