package com.example.demo.core.persistence.impl;

import com.example.demo.core.domain.Product;
import com.example.demo.core.persistence.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductRepositoryInMemory implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    private TreeSet<Product> sortedProducts = new TreeSet<>(Comparator.comparing(Product::price));

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void loadProducts() {
        try {
            String baseUrl = "https://jsonmock.hackerrank.com/api/inventory?page=";

            int page = 1;
            int totalPages;

            do {
                String jsonResponse = restTemplate.getForObject(baseUrl + page, String.class);
                JsonNode root = objectMapper.readTree(jsonResponse);

                totalPages = root.path("total_pages").asInt();

                JsonNode dataArray = root.path("data");
                for (JsonNode node : dataArray) {
                    Product product = new Product(
                            node.path("barcode").asString(),
                            node.path("item").asString(),
                            node.path("category").asString(),
                            node.path("price").asDouble(),
                            node.path("discount").asDouble(),
                            node.path("available").asLong()
                    );

                    sortedProducts.add(product);
                    products.add(product);
                }

                page++;

            } while (page <= totalPages);

            Collections.sort(products);

            System.out.println("Loaded " + products.size() + " products from API");

        } catch (Exception e) {
            log.error("Error during data load", e);
        }
    }

    @Override
    public Collection<Product> findAll() {
        return sortedProducts;
    }

    @Override
    public List<Product> search(double minPrice, double maxPrice) {
        return sortedProducts.stream().filter(it -> it.price() >= minPrice && it.price() <= maxPrice).toList();
    }
}
