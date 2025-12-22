package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockRecordService {

    private final StockRecordRepository repository;
    private final ProductRepository productRepo;
    private final WarehouseRepository warehouseRepo;

    public StockRecord create(Long productId, Long warehouseId, int qty) {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Warehouse warehouse = warehouseRepo.findById(warehouseId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));

        return repository.save(
                StockRecord.builder()
                        .product(product)
                        .warehouse(warehouse)
                        .quantity(qty)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }

    public List<StockRecord> getAll() {
        return repository.findAll();
    }
}
