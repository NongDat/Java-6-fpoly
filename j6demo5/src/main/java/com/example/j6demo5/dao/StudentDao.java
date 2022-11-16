package com.example.j6demo5.dao;

import com.example.j6demo5.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, String> {
}
