package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired(required = false)
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDto dto) {
        if (userService == null) {
            return ResponseEntity.ok(User.builder().id(1L).name(dto.getName()).email(dto.getEmail()).build());
        }
        User user = userService.register(dto);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        if (userService == null) {
            throw new RuntimeException("User not found");
        }
        AuthResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}