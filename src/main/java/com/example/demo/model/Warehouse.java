package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String warehouseName;
    
    @Column(nullable = false)
    private String location;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    public Warehouse() {}
    
    public Warehouse(Long id, String warehouseName, String location, LocalDateTime createdAt) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.location = location;
        this.createdAt = createdAt;
    }
    
    public static WarehouseBuilder builder() {
        return new WarehouseBuilder();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public static class WarehouseBuilder {
        private Long id;
        private String warehouseName;
        private String location;
        private LocalDateTime createdAt;
        
        public WarehouseBuilder id(Long id) { this.id = id; return this; }
        public WarehouseBuilder warehouseName(String warehouseName) { this.warehouseName = warehouseName; return this; }
        public WarehouseBuilder location(String location) { this.location = location; return this; }
        public WarehouseBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        
        public Warehouse build() {
            return new Warehouse(id, warehouseName, location, createdAt);
        }
    }
}