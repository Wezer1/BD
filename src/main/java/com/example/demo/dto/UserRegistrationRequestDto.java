package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class UserRegistrationRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    private Role role;

}
