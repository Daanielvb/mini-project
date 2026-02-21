package com.example.demo.core.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class SearchProductServiceTest {

    @InjectMocks
    private SearchProductService searchProductService;


    @Test
    void shouldReturnAllProducts(){

        var result = searchProductService.findAll();

    }
}
