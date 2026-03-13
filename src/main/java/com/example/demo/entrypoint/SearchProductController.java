package com.example.demo.entrypoint;

import com.example.demo.config.exception.CustomProblemDetail;
import com.example.demo.core.domain.Product;
import com.example.demo.core.service.SearchProductService;
import com.example.demo.entrypoint.response.ProductResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products/v1")
@RequiredArgsConstructor
@Validated
@Tag(name = "Products", description = "Management of products lyfecicle")
public class SearchProductController {

    private final SearchProductService searchProductService;

    @GetMapping
    public ResponseEntity<Collection<Product>> getAllProducts(){
        return ResponseEntity.ok(searchProductService.findAll());
    }

    @GetMapping("/search")
    @Operation(description = "search products by price range")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido criado",
                    content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "400", description = "Invalid range",
                    content = @Content(schema = @Schema(implementation = CustomProblemDetail.class))),
    })
    public ResponseEntity<Collection<Product>> search(@RequestParam @Min(value=0) double minPrice, @RequestParam @PositiveOrZero double maxPrice){
        return ResponseEntity.ok(searchProductService.findAllWithPriceInRange(minPrice, maxPrice));
    }

    @GetMapping("/sort")
    public ResponseEntity<Collection<ProductResponseDTO>> sort(){
        return ResponseEntity.ok(searchProductService.findAll().stream()
                .map(it -> new ProductResponseDTO(it.barCode(), it.price()))
                .toList());
    }




}
