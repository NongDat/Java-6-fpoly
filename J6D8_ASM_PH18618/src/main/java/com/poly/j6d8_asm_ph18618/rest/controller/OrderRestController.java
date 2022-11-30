package com.poly.j6d8_asm_ph18618.rest.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.poly.j6d8_asm_ph18618.entity.Order;
import com.poly.j6d8_asm_ph18618.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/status/update/{id}")
    public Order UpdateStatus(
            @PathVariable("id")Long id,
            @RequestBody Order order
    ) {
    return orderService.updateStatus(order);
    }

    @GetMapping("/list")
    public List<Order> getAll() {
        return orderService.getAll();
    }
}
