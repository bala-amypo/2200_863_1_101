package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumptionLogService {

    private final ConsumptionLogRepository logRepo;
    private final StockRecordRepository stockRepo;

    public ConsumptionLog logConsumption(Long stockId, int qty) {
        StockRecord stock = stockRepo.findById(stockId).orElseThrow();

        return logRepo.save(
                ConsumptionLog.builder()
                        .stockRecord(stock)
                        .consumedQty(qty)
                        .date(LocalDate.now())
                        .build()
        );
    }

    public List<ConsumptionLog> getAll() {
        return logRepo.findAll();
    }
}
