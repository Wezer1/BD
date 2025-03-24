package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationRequestDto;
import com.example.demo.dto.UserRegistrationResponseDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationResponseDto> registrationUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto){

        return ResponseEntity.ok(userService.saveUser(userRegistrationRequestDto));
    }
    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserRegistrationResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserRegistrationResponseDto> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PostMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserRegistrationResponseDto> changeUser(@PathVariable Integer userId,@RequestBody UserRegistrationRequestDto userRegistrationRequestDto){
        return ResponseEntity.ok(userService.changeUser(userId, userRegistrationRequestDto));
    }



    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserRegistrationResponseDto> deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
