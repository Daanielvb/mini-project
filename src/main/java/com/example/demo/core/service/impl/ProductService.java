package com.example.demo.core.service.impl;

import com.example.demo.core.domain.Product;
import com.example.demo.core.gateway.impl.KafkaProducer;
import com.example.demo.core.persistence.ProductRepository;
import com.example.demo.core.service.SearchProductService;
import com.example.demo.core.service.WriteProductService;
import com.example.demo.core.service.exception.CustomValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService implements SearchProductService, WriteProductService {

    private final ProductRepository productRepository;
    private final KafkaProducer producer;

    @Override
    public Collection<Product> findAll() {
        log.info("Finding all products");
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> findAllWithPriceInRange(double minPrice, double maxPrice) {
        log.info("Finding all products with price in range");
        isValidPrice(minPrice, maxPrice);
        return productRepository.search(minPrice, maxPrice);
    }

    private void isValidPrice(double minPrice, double maxPrice){
        Set<String> errors = new HashSet<>();
        if (minPrice < 0){
            errors.add(format("Min price %s is negative", minPrice));
        }
        if (maxPrice < 0){
            errors.add(format("Max price %s is negative", maxPrice));
        }
        if (minPrice >= maxPrice) {
            errors.add(format("Min price %s exceeds max price %s", minPrice, maxPrice));
        }
        if (!errors.isEmpty()){
            throw new CustomValidationException(errors, "Invalid price input");
        }
    }

    @Override
    public Collection<Product> findAllWithStock() {
        return productRepository.findAll().stream().filter(it -> it.available() >= 0).toList();
    }

    @Override
    public void add(Product product){
        productRepository.addProduct(product);
        producer.produce(product.barCode());
    }
}
