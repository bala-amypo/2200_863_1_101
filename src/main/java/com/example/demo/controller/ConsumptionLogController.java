package com.example.demo.controller;

import com.example.demo.model.ConsumptionLog;
import com.example.demo.service.ConsumptionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumption")
@RequiredArgsConstructor
public class ConsumptionLogController {

    private final ConsumptionLogService service;

    @PostMapping("/{stockId}/{qty}")
    public ConsumptionLog log(@PathVariable Long stockId,
                              @PathVariable int qty) {
        return service.logConsumption(stockId, qty);
    }

    @GetMapping
    public List<ConsumptionLog> getAll() {
        return service.getAll();
    }
}
