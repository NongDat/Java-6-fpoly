package com.example.j6demo4_resttemplate_springboot.dao;

import com.example.j6demo4_resttemplate_springboot.bean.Student;
import com.example.j6demo4_resttemplate_springboot.bean.StudentMap;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class StudentDao {
    RestTemplate rest = new RestTemplate();

    String url = "https://poly-java-6-dd2ca-default-rtdb.firebaseio.com/students.json";
    private String getUrl(String key) {
        return  url.replace(".json","/" + key + ".json");
    }

    public StudentMap findAll() {
        // đưa url -> nhận 1 tập hợp(Map) các student
        return rest.getForObject(url, StudentMap.class);
    }

    public Student findByKey(String key) {
        return rest.getForObject(getUrl(key), Student.class);
    }

    public String create(Student data) {
        HttpEntity<Student> entity = new HttpEntity<>(data); // data dc bọc trong cái entity
        JsonNode resp = rest.postForObject(url, entity, JsonNode.class);
        return resp.get("name").asText(); // key trả về
    }

    public Student update(String key, Student data) {
        HttpEntity<Student> entity = new HttpEntity<>(data);
        rest.put(getUrl(key), entity);
        return data;// thực hiện update xong trả lại data
    }

    public void delete(String key) {
        rest.delete(getUrl(key));
    }
}
