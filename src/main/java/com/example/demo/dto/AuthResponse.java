package com.example.demo.dto;

import java.util.Set;

public class AuthResponse {
    private String token;
    private Long userId;
    private String email;
    private Set<String> roles;
    
    public AuthResponse() {}
    
    public AuthResponse(String token, Long userId, String email, Set<String> roles) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }
    
    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
    
    public static class AuthResponseBuilder {
        private String token;
        private Long userId;
        private String email;
        private Set<String> roles;
        
        public AuthResponseBuilder token(String token) { this.token = token; return this; }
        public AuthResponseBuilder userId(Long userId) { this.userId = userId; return this; }
        public AuthResponseBuilder email(String email) { this.email = email; return this; }
        public AuthResponseBuilder roles(Set<String> roles) { this.roles = roles; return this; }
        
        public AuthResponse build() {
            return new AuthResponse(token, userId, email, roles);
        }
    }
}