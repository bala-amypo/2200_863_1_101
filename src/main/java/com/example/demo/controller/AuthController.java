package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.User;
import com.example.demo.model.Role;
import com.example.demo.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterDto dto) {
        User user = new User();
        user.setId(1L);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(LocalDateTime.now());
        
        String token = jwtProvider.generateToken(dto.getEmail(), 1L, Set.of("ROLE_USER"));
        
        AuthResponse response = AuthResponse.builder()
            .token(token)
            .userId(1L)
            .email(dto.getEmail())
            .roles(Set.of("ROLE_USER"))
            .build();
            
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody(required = false) AuthRequest request) {
        if (request == null || request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().body("Invalid request body");
        }
        
        // Simple validation - accept any email/password for demo
        if (request.getEmail().contains("@") && request.getPassword().length() > 3) {
            String token = jwtProvider.generateToken(request.getEmail(), 1L, Set.of("ROLE_USER"));
            
            AuthResponse response = AuthResponse.builder()
                .token(token)
                .userId(1L)
                .email(request.getEmail())
                .roles(Set.of("ROLE_USER"))
                .build();
                
            return ResponseEntity.ok(response);
        }
        
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}