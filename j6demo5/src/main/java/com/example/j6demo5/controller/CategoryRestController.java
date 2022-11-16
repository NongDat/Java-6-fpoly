package com.example.j6demo5.controller;

import com.example.j6demo5.dao.AccountDao;
import com.example.j6demo5.dao.AuthorityDao;
import com.example.j6demo5.dao.CategoryDao;
import com.example.j6demo5.dao.RoleDao;
import com.example.j6demo5.entity.Account;
import com.example.j6demo5.entity.Authority;
import com.example.j6demo5.entity.Category;
import com.example.j6demo5.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CategoryRestController {
    @Autowired
    CategoryDao dao;
    @Autowired
    AccountDao accountDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    AuthorityDao authorityDao;
    @GetMapping("/rest/authorities")
    public ResponseEntity<List<Authority>> authorities(Model model) {
        return ResponseEntity.ok(authorityDao.findAll());
    }
    @GetMapping("/rest/role")
    public ResponseEntity<List<Role>> role(Model model) {
        return ResponseEntity.ok(roleDao.findAll());
    }

    @GetMapping("/rest/acc")
    public ResponseEntity<List<Account>> acc(Model model) {
        return ResponseEntity.ok(accountDao.findAll());
    }

    @GetMapping("/rest/categories")
    public ResponseEntity<List<Category>> getAll(Model model) {
        return ResponseEntity.ok(dao.findAll());
    }

    @GetMapping("/rest/categories/{id}")
    public ResponseEntity<Category> getOne(@PathVariable("id") String id) {
        if(!dao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dao.findById(id).get());
    }

    @PostMapping("/rest/categories")
    public ResponseEntity<Category> post(@RequestBody Category category) {
        if(dao.existsById(category.getId())) {
            return ResponseEntity.badRequest().build();
        }
        dao.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/rest/categories/{id}")
    public ResponseEntity<Category> put(
            @PathVariable("id") String id,
            @RequestBody Category category
    ) {
        if(!dao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dao.save(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/rest/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        if(!dao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        dao.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
