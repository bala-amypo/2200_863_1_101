package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.StockRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogService {

    private final ConsumptionLogRepository repository;
    private final StockRecordRepository stockRepo;

    public ConsumptionLog log(Long stockId, int qty) {
        StockRecord stock = stockRepo.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found"));

        return repository.save(
                ConsumptionLog.builder()
                        .stockRecord(stock)
                        .consumedQty(qty)
                        .date(LocalDate.now())
                        .build()
        );
    }

    public List<ConsumptionLog> getAll() {
        return repository.findAll();
    }
}
