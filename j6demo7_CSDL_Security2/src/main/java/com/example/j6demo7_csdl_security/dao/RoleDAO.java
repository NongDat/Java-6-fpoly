package com.example.j6demo7_csdl_security.dao;

import com.example.j6demo7_csdl_security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDAO extends JpaRepository<Role, String> {
}
