package com.example.demo.entrypoint;

import com.example.demo.core.domain.Product;
import com.example.demo.core.service.SearchProductService;
import com.example.demo.entrypoint.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class SearchProductController {

    private final SearchProductService searchProductService;

    @GetMapping
    public ResponseEntity<Collection<Product>> getAllProducts(){
        return ResponseEntity.ok(searchProductService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Collection<Product>> search(@RequestParam double minPrice, @RequestParam double maxPrice){
        return ResponseEntity.ok(searchProductService.findAllWithPriceInRange(minPrice, maxPrice));
    }

    @GetMapping("/sort")
    public ResponseEntity<Collection<ProductResponseDTO>> sort(){
        return ResponseEntity.ok(searchProductService.findAll().stream()
                .map(it -> new ProductResponseDTO(it.barCode(), it.price()))
                .toList());
    }


}
