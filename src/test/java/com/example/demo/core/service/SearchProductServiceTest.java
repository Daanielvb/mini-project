package com.example.demo.core.service;

import com.example.demo.core.persistence.ProductRepository;
import com.example.demo.core.service.exception.CustomValidationException;
import com.example.demo.core.service.impl.SearchProductServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductServiceImplementation searchProductService;

    @Test
    void shouldReturnAllProducts(){
        var result = searchProductService.findAll();

        verify(productRepository).findAll();
        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnFilteredProductsByPrice(){
        int maxPrice = 100;
        int minPrice = 10;


        when(productRepository.search(anyDouble(), anyDouble())).thenReturn(emptyList());

        var result = searchProductService.findAllWithPriceInRange(minPrice, maxPrice);

        assertThat(result).isEmpty();
        verify(productRepository).search(minPrice, maxPrice);
    }

    @Test
    void shouldRefuseInvalidPriceRange(){
        assertThatThrownBy( () -> searchProductService.findAllWithPriceInRange(0, 0))
                .isInstanceOf(CustomValidationException.class)
                .hasMessage("Invalid price input")
                .satisfies(e -> {
                    assertThat(((CustomValidationException) e).getErrors()).isEqualTo(Set.of("Min price 0.0 exceeds max price 0.0"));

                });
    }
}
