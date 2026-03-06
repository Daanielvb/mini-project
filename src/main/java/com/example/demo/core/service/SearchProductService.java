package com.example.demo.core.service;

import com.example.demo.core.domain.Product;

import java.util.Collection;

public interface SearchProductService {

    Collection<Product> findAll();

    Collection<Product> findAllWithPriceInRange(double minPrice, double maxPrice);

    Collection<Product> findAllWithStock();
}
