package com.example.demo.entrypoint;

import com.example.demo.core.domain.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldListAllProducts() throws Exception {
        //given
        mockMvc.perform(get("/products/v1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldListAllProductsWithinPrice() throws Exception {
        //given
        String minPrice = "100";
        String maxPrice = "1000";
        long start = System.nanoTime();
        var response = mockMvc.perform(get("/products/v1/search")
                .param("minPrice", minPrice)
                .param("maxPrice", maxPrice))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String content = response.getResponse().getContentAsString();
        List<Product> actualProducts = objectMapper.readValue(content, new TypeReference<List<Product>>() {});

        assertThat(actualProducts).hasSize(415);
        System.out.println(format("took %d ms to finish processing", System.nanoTime() - start));
    }

    @Test
    public void shouldRejectInvalidMinMaxPrice() throws Exception {
        //given
        String minPrice = "30";
        String maxPrice = "20";

        mockMvc.perform(get("/products/v1/search")
                        .param("minPrice", minPrice)
                        .param("maxPrice", maxPrice))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.detail").value("Invalid price input"))
                .andExpect(jsonPath("$.errors[0]").value("Min price 30.0 exceeds max price 20.0")
                );
    }
}
