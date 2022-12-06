package com.poly.j6d8_asm_ph18618.service;

import com.poly.j6d8_asm_ph18618.entity.Account;

import java.util.List;

public interface AccountService {
    Account findById(String username);

    List<Account> findAll();

    void save(Account account);
    List<Account> getAdministrators();
}
