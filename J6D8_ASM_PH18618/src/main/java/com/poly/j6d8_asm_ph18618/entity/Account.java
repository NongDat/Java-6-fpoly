package com.poly.j6d8_asm_ph18618.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "Fullname")
    private String fullname;
    @Column(name = "Email")
    private String email;
    @Column(name = "Photo")
    private String photo;
    @Column(name = "Activated")
    private Boolean activated;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "account")
    List<Authority> authorities;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    List<Order> orders;


}
