package com.poly.j6d8_asm_ph18618.service;

import com.poly.j6d8_asm_ph18618.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getAllByOrder(Long id);
}
