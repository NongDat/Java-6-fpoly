package com.example.j6demo7_csdl_security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    String password;
    @Column(name = "Fullname")
    String fullname;
    @Column(name = "Email")
    String email;
    @Column(name = "Photo")
    String photo;
    @Column(name = "Activated")
    Boolean activated;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Authority> authorities;
}
