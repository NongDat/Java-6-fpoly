package com.example.j6demo5.dao;

import com.example.j6demo5.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, String> {
}
