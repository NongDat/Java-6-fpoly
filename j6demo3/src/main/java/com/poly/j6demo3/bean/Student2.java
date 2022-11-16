package com.poly.j6demo3.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student2 {
        @NotBlank(message = "Không để trống email")
        String email;
        @NotBlank(message = "Không để trống fullname")
        String fullName;
        @NotNull(message = "Không để trống điểm")
        @PositiveOrZero(message = "Điểm phải lớn hơn hoặc bằng 0")
        @Max(value = 10,message = "Điểm phải nhỏ hơn hoặc bằng 10")
        Double marks;
        @NotNull(message = "Chọn giới tính")
        Boolean gender;
        @NotBlank(message = "Không để trống country")
        String country;
}
