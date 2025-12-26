package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ConsumptionLog;
import com.example.demo.model.PredictionRule;
import com.example.demo.model.StockRecord;
import com.example.demo.repository.ConsumptionLogRepository;
import com.example.demo.repository.PredictionRuleRepository;
import com.example.demo.repository.StockRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionServiceImpl implements PredictionService {
    
    @Autowired
    private PredictionRuleRepository predictionRuleRepository;
    
    @Autowired
    private StockRecordRepository stockRecordRepository;
    
    @Autowired
    private ConsumptionLogRepository consumptionLogRepository;
    
    @Override
    public LocalDate predictRestockDate(Long stockRecordId) {
        StockRecord stockRecord = stockRecordRepository.findById(stockRecordId)
            .orElseThrow(() -> new ResourceNotFoundException("StockRecord not found"));
        
        List<ConsumptionLog> logs = consumptionLogRepository.findByStockRecordId(stockRecordId);
        
        // Simple prediction: assume 1 unit per day if no logs
        double dailyUsage = 1.0;
        if (!logs.isEmpty()) {
            int totalConsumed = logs.stream().mapToInt(ConsumptionLog::getConsumedQuantity).sum();
            dailyUsage = Math.max(1.0, totalConsumed / 7.0); // assume 7 days window
        }
        
        int daysUntilReorder = (int) Math.ceil((stockRecord.getCurrentQuantity() - stockRecord.getReorderThreshold()) / dailyUsage);
        return LocalDate.now().plusDays(Math.max(1, daysUntilReorder));
    }
    
    @Override
    public List<PredictionRule> getAllRules() {
        return predictionRuleRepository.findAll();
    }
    
    @Override
    public PredictionRule createRule(PredictionRule rule) {
        if (rule.getAverageDaysWindow() <= 0) {
            throw new IllegalArgumentException("Average days window must be > 0");
        }
        
        if (rule.getMinDailyUsage() > rule.getMaxDailyUsage()) {
            throw new IllegalArgumentException("Min daily usage must be <= max daily usage");
        }
        
        if (rule.getRuleName() != null && predictionRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Rule name already exists");
        }
        
        if (rule.getCreatedAt() == null) {
            rule.setCreatedAt(LocalDateTime.now());
        }
        
        return predictionRuleRepository.save(rule);
    }
}