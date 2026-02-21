package com.example.demo.core.service.impl;

import com.example.demo.core.domain.Product;
import com.example.demo.core.persistence.ProductRepository;
import com.example.demo.core.service.SearchProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SearchProductServiceImplementation implements SearchProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllWithPriceInRange(double minPrice, double maxPrice) {
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException(format("Min price %s exceeds max price %s", minPrice, maxPrice));
        }
        return productRepository.search(minPrice, maxPrice);
    }
}
