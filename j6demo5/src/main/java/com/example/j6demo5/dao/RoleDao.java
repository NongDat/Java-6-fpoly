package com.example.j6demo5.dao;

import com.example.j6demo5.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, String> {
}
