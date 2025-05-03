package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRegistrationResponseDto {

    private Integer id;

    @NotBlank
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    private Role role;

}
