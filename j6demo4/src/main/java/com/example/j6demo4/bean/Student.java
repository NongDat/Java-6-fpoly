package com.example.j6demo4.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Student {
    String email;
    String fullname;
    Double marks;
    Boolean gender;
    String country;

    // chuyển đổi thông tin student thành array -> đưa lên table của giao diện
    // Phục vụ cho giao diện
    @JsonIgnore
    public Object[] getArray() {
        return new Object[]{
                getEmail(),
                getFullname(),
                getMarks(),
                getGender(),
                getCountry()
        };
    }
}
