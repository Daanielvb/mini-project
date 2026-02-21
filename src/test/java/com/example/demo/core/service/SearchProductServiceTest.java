package com.example.demo.core.service;

import com.example.demo.core.persistence.ProductRepository;
import com.example.demo.core.service.impl.SearchProductServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

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
}
