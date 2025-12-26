package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(unique = true, nullable = false)
    private String sku;
    
    private String category;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    public Product() {}
    
    public Product(Long id, String productName, String sku, String category, LocalDateTime createdAt) {
        this.id = id;
        this.productName = productName;
        this.sku = sku;
        this.category = category;
        this.createdAt = createdAt;
    }
    
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public static class ProductBuilder {
        private Long id;
        private String productName;
        private String sku;
        private String category;
        private LocalDateTime createdAt;
        
        public ProductBuilder id(Long id) { this.id = id; return this; }
        public ProductBuilder productName(String productName) { this.productName = productName; return this; }
        public ProductBuilder sku(String sku) { this.sku = sku; return this; }
        public ProductBuilder category(String category) { this.category = category; return this; }
        public ProductBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        
        public Product build() {
            return new Product(id, productName, sku, category, createdAt);
        }
    }
}