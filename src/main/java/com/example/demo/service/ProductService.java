package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product createProduct(Product product) {
        if (product.getProductName() == null || product.getProductName().isBlank())
            throw new IllegalArgumentException("Product name required");

        repository.findBySku(product.getSku()).ifPresent(p -> {
            throw new IllegalArgumentException("SKU already exists");
        });

        product.setCreatedAt(LocalDateTime.now());
        return repository.save(product);
    }

    public Product getProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
}
