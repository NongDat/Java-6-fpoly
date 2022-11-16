package com.poly.j6d8_asm_ph18618.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
    @RequestMapping("/order/checkout")
    public String checkout() {
        return "/order/checkout";
    }

    @RequestMapping("/order/list")
    public String list() {
        return "/order/list";
    }

    @RequestMapping("/order/detail/{id}")
    public String detail() {
        return "/order/detail";
    }

}
