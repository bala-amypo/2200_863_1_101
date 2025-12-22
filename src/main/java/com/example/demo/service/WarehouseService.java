package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Warehouse;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository repository;

    public Warehouse save(Warehouse warehouse) {
        return repository.save(warehouse);
    }

    public Warehouse getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found"));
    }

    public List<Warehouse> getAll() {
        return repository.findAll();
    }
}
