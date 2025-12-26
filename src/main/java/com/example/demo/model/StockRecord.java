package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_records", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"product_id", "warehouse_id"})
})
public class StockRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;
    
    @Column(nullable = false)
    private Integer currentQuantity;
    
    @Column(nullable = false)
    private Integer reorderThreshold;
    
    private LocalDateTime lastUpdated;
    
    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    
    public StockRecord() {}
    
    public StockRecord(Long id, Product product, Warehouse warehouse, Integer currentQuantity, Integer reorderThreshold, LocalDateTime lastUpdated) {
        this.id = id;
        this.product = product;
        this.warehouse = warehouse;
        this.currentQuantity = currentQuantity;
        this.reorderThreshold = reorderThreshold;
        this.lastUpdated = lastUpdated;
    }
    
    public static StockRecordBuilder builder() {
        return new StockRecordBuilder();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public Warehouse getWarehouse() { return warehouse; }
    public void setWarehouse(Warehouse warehouse) { this.warehouse = warehouse; }
    
    public Integer getCurrentQuantity() { return currentQuantity; }
    public void setCurrentQuantity(Integer currentQuantity) { this.currentQuantity = currentQuantity; }
    
    public Integer getReorderThreshold() { return reorderThreshold; }
    public void setReorderThreshold(Integer reorderThreshold) { this.reorderThreshold = reorderThreshold; }
    
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
    
    public static class StockRecordBuilder {
        private Long id;
        private Product product;
        private Warehouse warehouse;
        private Integer currentQuantity;
        private Integer reorderThreshold;
        private LocalDateTime lastUpdated;
        
        public StockRecordBuilder id(Long id) { this.id = id; return this; }
        public StockRecordBuilder product(Product product) { this.product = product; return this; }
        public StockRecordBuilder warehouse(Warehouse warehouse) { this.warehouse = warehouse; return this; }
        public StockRecordBuilder currentQuantity(Integer currentQuantity) { this.currentQuantity = currentQuantity; return this; }
        public StockRecordBuilder reorderThreshold(Integer reorderThreshold) { this.reorderThreshold = reorderThreshold; return this; }
        public StockRecordBuilder lastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; return this; }
        
        public StockRecord build() {
            return new StockRecord(id, product, warehouse, currentQuantity, reorderThreshold, lastUpdated);
        }
    }
}