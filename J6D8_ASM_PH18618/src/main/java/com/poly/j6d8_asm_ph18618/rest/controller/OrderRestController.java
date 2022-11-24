package com.poly.j6d8_asm_ph18618.rest.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.j6d8_asm_ph18618.entity.Order;
import com.poly.j6d8_asm_ph18618.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping()
    public Order create(@RequestBody JsonNode orderData) {
        return orderService.create(orderData);
    }
}
