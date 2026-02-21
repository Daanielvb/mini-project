package com.example.demo.core.service;

import com.example.demo.core.domain.Product;

import java.util.List;

public interface SearchProductService {

    List<Product> findAll();

    List<Product> findAllWithPriceInRange(double minPrice, double maxPrice);
}
