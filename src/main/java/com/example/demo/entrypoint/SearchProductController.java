package com.example.demo.entrypoint;

import com.example.demo.core.domain.Product;
import com.example.demo.core.service.SearchProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class SearchProductController {

    private final SearchProductService searchProductService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(searchProductService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> search(@RequestParam double minPrice, @RequestParam double maxPrice){
        return ResponseEntity.ok(searchProductService.findAllWithPriceInRange(minPrice, maxPrice));
    }



}
