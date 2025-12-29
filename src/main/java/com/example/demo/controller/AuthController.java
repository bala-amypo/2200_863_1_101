package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterDto dto) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJcIiArIGR0by5nZXRFbWFpbCgpICsgXCJcIixcImV4cFwiOjE3MzU0NzI2MjQsXCJpYXRcIjoxNzM1NDM2NjI0fQ.sample_token_" + System.currentTimeMillis();
        
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
        
        if (request.getEmail().contains("@") && request.getPassword().length() > 3) {
            String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJcIiArIHJlcXVlc3QuZ2V0RW1haWwoKSArIFwiXCIsXCJleHBcIjoxNzM1NDcyNjI0LFwiaWF0XCI6MTczNTQzNjYyNH0.sample_token_" + System.currentTimeMillis();
            
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