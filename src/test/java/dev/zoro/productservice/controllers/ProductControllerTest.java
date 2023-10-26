package dev.zoro.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void getAllProductsReturnsEmptyListWhenNoProductsExist() throws Exception {
        when(productService.getAllProducts())
                .thenReturn(new ArrayList<>());

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(content().json("[]"));
    }

    @Test
    void returnsProductsWhenProductsExist() throws Exception {
        ArrayList<GenericProductDto>products = new ArrayList<>();

        GenericProductDto product = new GenericProductDto();
        GenericProductDto product1 = new GenericProductDto();
        GenericProductDto product2 = new GenericProductDto();
        products.add(product);
        products.add(product1);
        products.add(product2);

        when(productService.getAllProducts())
                .thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().is(200))
                .andExpect(header().exists("Content-Type"))
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

    @Test
    void createProductShouldCreateANewProduct() throws Exception {
        GenericProductDto product = new GenericProductDto();
        product.setTitle("New Product");
        product.setDescription("New Product Description");
        product.setPrice(420.0);
        product.setCategory("New Category");
        product.setImage("https://sample.image/1");

        GenericProductDto expectedProduct = new GenericProductDto();
        expectedProduct.setTitle("New Product");
        expectedProduct.setDescription("New Product Description");
        expectedProduct.setPrice(420.0);
        expectedProduct.setCategory("New Category");
        expectedProduct.setImage("https://sample.image/1");

        when(productService.createProduct(any()))
                .thenReturn(expectedProduct);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product))
                )
                .andExpect(status().is(200))
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)))
                .andExpect(jsonPath("$.title", is("New Product")));
    }

}
