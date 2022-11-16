package com.example.j6demo4.dao;

import com.example.j6demo4.bean.Student;
import com.example.j6demo4.bean.StudentMap;
import com.example.j6demo4.rest.Rest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudentDao {
    ObjectMapper mapper = new ObjectMapper();

    public StudentMap findAll() {
        JsonNode resp = Rest.get("/students");
        return mapper.convertValue(resp, StudentMap.class);
    }

    public Student findByKey(String key) {
        JsonNode resp = Rest.get("/students" + key);
        return mapper.convertValue(resp, Student.class);
    }

    public String create(Student data) {
        JsonNode resp = Rest.post("/students", data);
        return resp.get("name").asText();// create xong sẽ nhận đc key chính là resp.get("name").asText();
    }

    public Student update(String key, Student data) {
        JsonNode resp = Rest.put("/students/" + key, data);
        return mapper.convertValue(resp, Student.class);
    }

}
