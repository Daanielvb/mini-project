package com.example.demo.core.service.impl;

import com.example.demo.core.domain.Product;
import com.example.demo.core.persistence.ProductRepository;
import com.example.demo.core.service.SearchProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchProductServiceImplementation implements SearchProductService {

    private final ProductRepository productRepository;

    @Override
    public Collection<Product> findAll() {
        log.info("Finding all products");
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> findAllWithPriceInRange(double minPrice, double maxPrice) {
        log.info("Finding all products with price in range");
        if (minPrice > maxPrice) {
            System.out.println("whatever");
            throw new IllegalArgumentException(format("Min price %s exceeds max price %s", minPrice, maxPrice));
        }
        return productRepository.search(minPrice, maxPrice);
    }

    @Override
    public Collection<Product> findAllWithStock() {
        return List.of();
    }
}
