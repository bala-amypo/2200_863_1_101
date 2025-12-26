package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDto dto) {
        User user = User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .password(dto.getPassword())
            .roles(Set.of(Role.ROLE_USER))
            .createdAt(LocalDateTime.now())
            .build();
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        throw new RuntimeException("User not found");
    }
}