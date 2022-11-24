package com.poly.j6d8_asm_ph18618.service.serviceImpl;

import com.poly.j6d8_asm_ph18618.dao.RoleDAO;
import com.poly.j6d8_asm_ph18618.entity.Role;
import com.poly.j6d8_asm_ph18618.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDAO roleDAO;

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }
}
