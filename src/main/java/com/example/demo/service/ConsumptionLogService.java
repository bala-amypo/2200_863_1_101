package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumption")
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    public ConsumptionLogController(ConsumptionLogService service) {
        this.service = service;
    }

    @PostMapping("/{productId}")
    public ConsumptionLog logConsumption(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        return service.create(productId, quantity);
    }

    @GetMapping
    public List<ConsumptionLog> getAll() {
        return service.getAll();
    }
}
