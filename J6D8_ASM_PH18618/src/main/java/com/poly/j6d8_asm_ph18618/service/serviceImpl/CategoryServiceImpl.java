package com.poly.j6d8_asm_ph18618.service.serviceImpl;

import com.poly.j6d8_asm_ph18618.dao.CategoryDAO;
import com.poly.j6d8_asm_ph18618.entity.Category;
import com.poly.j6d8_asm_ph18618.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
}
