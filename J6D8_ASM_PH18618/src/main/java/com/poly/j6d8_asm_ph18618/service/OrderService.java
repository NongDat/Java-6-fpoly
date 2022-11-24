package com.poly.j6d8_asm_ph18618.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.poly.j6d8_asm_ph18618.entity.Order;

import java.util.List;

public interface OrderService {
    Order create(JsonNode orderData);

    Order findById(Long id);
    List<Order> findByUserName(String username);
}
