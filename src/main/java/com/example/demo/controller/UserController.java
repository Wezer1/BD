package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO){
//        clientDTO.setStatus(Status.valueOf("ACTIVE"));
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<UserDTO> changeUser(@PathVariable Integer userId,
                                                  @RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.changeUser(userId, userDTO));
    }
}