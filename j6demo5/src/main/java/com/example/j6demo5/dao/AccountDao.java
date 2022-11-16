package com.example.j6demo5.dao;

import com.example.j6demo5.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountDao extends JpaRepository<Account, String> {
}
