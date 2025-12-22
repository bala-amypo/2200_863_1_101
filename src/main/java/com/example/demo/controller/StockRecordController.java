package com.example.demo.controller;

import com.example.demo.model.StockRecord;
import com.example.demo.service.StockRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRecordController {

    private final StockRecordService service;

    // Manual constructor for dependency injection
    public StockRecordController(StockRecordService service) {
        this.service = service;
    }

    @PostMapping("/{productId}/{warehouseId}")
    public StockRecord create(
            @PathVariable Long productId,
            @PathVariable Long warehouseId,
            @RequestBody StockRecord record) {
        return service.createStockRecord(productId, wareh
