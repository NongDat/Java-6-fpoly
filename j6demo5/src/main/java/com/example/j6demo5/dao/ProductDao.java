package com.example.j6demo5.dao;

import com.example.j6demo5.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
