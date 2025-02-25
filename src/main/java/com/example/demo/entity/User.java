package com.example.demo.entity;

import com.example.demo.model.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "lastname", length = 64)
    private String lastname;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "role", length = 20)
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
