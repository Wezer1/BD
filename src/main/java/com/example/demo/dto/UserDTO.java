package com.example.demo.dto;

import com.example.demo.model.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String lastname;

    @NotBlank
    private String name;

    private Role role;
}
