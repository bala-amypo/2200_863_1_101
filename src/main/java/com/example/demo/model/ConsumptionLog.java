package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "consumption_logs")
public class ConsumptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private StockRecord stockRecord;

    private Integer consumedQuantity;
    private LocalDate consumedDate;

    public ConsumptionLog() {}

    public Long getId() { return id; }
    public StockRecord getStockRecord() { return stockRecord; }
    public Integer getConsumedQuantity() { return consumedQuantity; }
    public LocalDate getConsumedDate() { return consumedDate; }

    public void setId(Long id) { this.id = id; }
    public void setStockRecord(StockRecord stockRecord) { this.stockRecord = stockRecord; }
    public void setConsumedQuantity(Integer consumedQuantity) { this.consumedQuantity = consumedQuantity; }
    public void setConsumedDate(LocalDate consumedDate) { this.consumedDate = consumedDate; }
}
