package com.example.demo.core.domain;

import java.util.UUID;

public record Product(
        String barCode,
        String item,
        String category,
        Double price,
        Double discount,
        Long available
) implements Comparable<Product> {

    public static Product makeOne(double price){
        return new Product(UUID.randomUUID().toString(), "item", "category", price, 1.0, 1L);
    }

    @Override
    public int compareTo(Product p) {
        return Double.compare(this.price(), p.price());
    }
}



