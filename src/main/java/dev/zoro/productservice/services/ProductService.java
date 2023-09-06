package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.models.Product;
import org.springframework.stereotype.Service;


public interface ProductService {
    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto product);
    GenericProductDto[] getAllProducts();
    GenericProductDto deleteProductById(Long id);
    GenericProductDto updateProductById(Long id, GenericProductDto product);
}
