package com.example.demo.core.persistence;

import com.example.demo.core.domain.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    List<Product> search(double minPrice, double maxPrice);

}
