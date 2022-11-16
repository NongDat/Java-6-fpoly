package com.poly.j6d8_asm_ph18618.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {
    @RequestMapping("/product/list")
    public String list() {
        System.out.println("hello");
        System.out.println("Nông Đạt");
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail() {
        return "product/detail";
    }
}
