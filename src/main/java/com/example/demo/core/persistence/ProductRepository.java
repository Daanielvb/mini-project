package com.example.demo.core.persistence;

import com.example.demo.core.domain.Product;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ProductRepository {

    Collection<Product> findAll();

    Collection<Product> search(double minPrice, double maxPrice);

}
