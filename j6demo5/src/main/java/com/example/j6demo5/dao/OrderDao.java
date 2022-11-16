package com.example.j6demo5.dao;

import com.example.j6demo5.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Long> {
}
