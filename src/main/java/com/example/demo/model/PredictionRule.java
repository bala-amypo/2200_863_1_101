package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_rules")
public class PredictionRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String ruleName;
    
    @Column(nullable = false)
    private Integer averageDaysWindow;
    
    @Column(nullable = false)
    private Integer minDailyUsage;
    
    @Column(nullable = false)
    private Integer maxDailyUsage;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    public PredictionRule() {}
    
    public PredictionRule(Long id, String ruleName, Integer averageDaysWindow, Integer minDailyUsage, Integer maxDailyUsage, LocalDateTime createdAt) {
        this.id = id;
        this.ruleName = ruleName;
        this.averageDaysWindow = averageDaysWindow;
        this.minDailyUsage = minDailyUsage;
        this.maxDailyUsage = maxDailyUsage;
        this.createdAt = createdAt;
    }
    
    public static PredictionRuleBuilder builder() {
        return new PredictionRuleBuilder();
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    
    public Integer getAverageDaysWindow() { return averageDaysWindow; }
    public void setAverageDaysWindow(Integer averageDaysWindow) { this.averageDaysWindow = averageDaysWindow; }
    
    public Integer getMinDailyUsage() { return minDailyUsage; }
    public void setMinDailyUsage(Integer minDailyUsage) { this.minDailyUsage = minDailyUsage; }
    
    public Integer getMaxDailyUsage() { return maxDailyUsage; }
    public void setMaxDailyUsage(Integer maxDailyUsage) { this.maxDailyUsage = maxDailyUsage; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public static class PredictionRuleBuilder {
        private Long id;
        private String ruleName;
        private Integer averageDaysWindow;
        private Integer minDailyUsage;
        private Integer maxDailyUsage;
        private LocalDateTime createdAt;
        
        public PredictionRuleBuilder id(Long id) { this.id = id; return this; }
        public PredictionRuleBuilder ruleName(String ruleName) { this.ruleName = ruleName; return this; }
        public PredictionRuleBuilder averageDaysWindow(Integer averageDaysWindow) { this.averageDaysWindow = averageDaysWindow; return this; }
        public PredictionRuleBuilder minDailyUsage(Integer minDailyUsage) { this.minDailyUsage = minDailyUsage; return this; }
        public PredictionRuleBuilder maxDailyUsage(Integer maxDailyUsage) { this.maxDailyUsage = maxDailyUsage; return this; }
        public PredictionRuleBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        
        public PredictionRule build() {
            return new PredictionRule(id, ruleName, averageDaysWindow, minDailyUsage, maxDailyUsage, createdAt);
        }
    }
}