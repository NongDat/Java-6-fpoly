package com.example.j6demo4_resttemplate_springboot.controller;

import com.example.j6demo4_resttemplate_springboot.bean.Student;
import com.example.j6demo4_resttemplate_springboot.bean.StudentMap;
import com.example.j6demo4_resttemplate_springboot.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
    @Autowired
    StudentDao dao;

    @RequestMapping("/student/index")
    public String index(Model model) {
        Student student = new Student("", "", 0.0, true, "VN");
        model.addAttribute("form", student);
        StudentMap map = dao.findAll();
        model.addAttribute("items", map);
        return "student/index";
    }

    @RequestMapping("/student/edit/{key}")
    public String edit(
            Model model,
            @PathVariable("key") String key
    ) {
        model.addAttribute("key", key);
        Student student = dao.findByKey(key);
        model.addAttribute("form", student);
        StudentMap map = dao.findAll();
        model.addAttribute("items", map);
        return "student/index";
    }

    @RequestMapping("/student/create")
    public String create(Student student) {
        dao.create(student);
        return "redirect:/student/index";
    }

    @RequestMapping("/student/update/{key}")
    public String update(
            @PathVariable("key") String key,
            Student student
    ) {
        dao.update(key, student);
        System.out.println("helo");
        return "redirect:/student/edit/" + key;
    }

    @RequestMapping("/student/delete/{key}")
    public String delete(@PathVariable("key") String key) {
        dao.delete(key);
        return "redirect:/student/index";
    }
}
