package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PredictionService {

    private final PredictionRuleRepository ruleRepo;
    private final StockRecordRepository stockRepo;
    private final ConsumptionLogRepository logRepo;

    public PredictionService(
            PredictionRuleRepository ruleRepo,
            StockRecordRepository stockRepo,
            ConsumptionLogRepository logRepo) {
        this.ruleRepo = ruleRepo;
        this.stockRepo = stockRepo;
        this.logRepo = logRepo;
    }

    public List<PredictionRule> getRules() {
        return ruleRepo.findAll();
    }

    public List<ConsumptionLog> getConsumption(Long productId, LocalDate from) {
        return logRepo.findByProductIdAndDateAfter(productId, from);
    }
}
