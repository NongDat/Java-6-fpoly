package com.example.j6demo5.controller;

import com.example.j6demo5.dao.StudentDao;
import com.example.j6demo5.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") // cho phép bên ngoài truy xuất vào thoải mái k ngăn cản gì cả
@RestController // rest API thuần túy
public class StudentRestController {
    @Autowired
    StudentDao dao;

    @GetMapping("/rest/students")
    public List<Student> getAll(Model model) {
        return dao.findAll();
    }

    @GetMapping("/rest/students/{email}")
    public Student getOne(@PathVariable("email") String email) {
        return dao.findById(email).get();
    }

    @PostMapping("/rest/students")
    public Student post(@RequestBody Student student) {
//        Khi post sv lên thì ta nhận đc sinh viên >>@RequestBody Student student<<
//        ta đẩy vào database rồi ta trả về student
//        fireBase sau khi insert trả về key
//        Con API này của mk tạo mk trả gì là việc của mk OK!
//        Trường hợp này mk thích trả về student cơ!!!
        dao.save(student);
        return student;
    }

    @PutMapping("/rest/students/{email}")
    public Student put(
            @PathVariable("email") String email,
            @RequestBody Student student
//            put hoặc post đưa dữ liệu từ consumer lên REST API thì ở REST API
//            nhận đc dữ liệu json đó bằng @RequestBody
    ) {
        dao.save(student);
        return student;
    }

    @DeleteMapping("/rest/students/{email}")
    public  void delete(@PathVariable("email") String email) {
        dao.deleteById(email);
    }
}
