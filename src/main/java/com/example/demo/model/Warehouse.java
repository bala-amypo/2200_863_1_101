package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String warehouseName;
    private String location;
    private LocalDateTime createdAt;

    public Warehouse() {}

    public Long getId() { return id; }
    public String getWarehouseName() { return warehouseName; }
    public String getLocation() { return location; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    public void setLocation(String location) { this.location = location; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
