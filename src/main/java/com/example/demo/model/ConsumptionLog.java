package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs")
public class ConsumptionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_record_id", nullable = false)
    private StockRecord stockRecord;
    
    @Column(nullable = false)
    private Integer consumedQuantity;
    
    @Column(nullable = false)
    private LocalDate consumedDate;
    
    public ConsumptionLog() {}
    
    public ConsumptionLog(Long id, StockRecord stockRecord, Integer consumedQuantity, LocalDate consumedDate) {
        this.id = id;
        this.stockRecord = stockRecord;
        this.consumedQuantity = consumedQuantity;
        this.consumedDate = consumedDate;
    }
    
    public static ConsumptionLogBuilder builder() {
        return new ConsumptionLogBuilder();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public StockRecord getStockRecord() { return stockRecord; }
    public void setStockRecord(StockRecord stockRecord) { this.stockRecord = stockRecord; }
    
    public Integer getConsumedQuantity() { return consumedQuantity; }
    public void setConsumedQuantity(Integer consumedQuantity) { this.consumedQuantity = consumedQuantity; }
    
    public LocalDate getConsumedDate() { return consumedDate; }
    public void setConsumedDate(LocalDate consumedDate) { this.consumedDate = consumedDate; }
    
    public static class ConsumptionLogBuilder {
        private Long id;
        private StockRecord stockRecord;
        private Integer consumedQuantity;
        private LocalDate consumedDate;
        
        public ConsumptionLogBuilder id(Long id) { this.id = id; return this; }
        public ConsumptionLogBuilder stockRecord(StockRecord stockRecord) { this.stockRecord = stockRecord; return this; }
        public ConsumptionLogBuilder consumedQuantity(Integer consumedQuantity) { this.consumedQuantity = consumedQuantity; return this; }
        public ConsumptionLogBuilder consumedDate(LocalDate consumedDate) { this.consumedDate = consumedDate; return this; }
        
        public ConsumptionLog build() {
            return new ConsumptionLog(id, stockRecord, consumedQuantity, consumedDate);
        }
    }
}