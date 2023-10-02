package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    GenericProductDto getProductById(UUID id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(UUID id) throws NotFoundException;
    GenericProductDto updateProductById(UUID id, GenericProductDto product);
}
