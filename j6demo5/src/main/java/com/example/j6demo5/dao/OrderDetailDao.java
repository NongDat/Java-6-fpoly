package com.example.j6demo5.dao;

import com.example.j6demo5.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Long> {
}
