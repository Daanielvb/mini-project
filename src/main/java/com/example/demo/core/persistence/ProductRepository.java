package com.example.demo.core.persistence;

import com.example.demo.core.domain.Product;

import java.util.Collection;

public interface ProductRepository {

    Collection<Product> findAll();

    Collection<Product> search(double minPrice, double maxPrice);

    void addProduct(Product product);

}
