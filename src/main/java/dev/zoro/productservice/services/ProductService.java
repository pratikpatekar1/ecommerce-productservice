package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.CategoryDto;
import dev.zoro.productservice.dtos.CategoryResponseDto;
import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.security.JwtObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ProductService {
    GenericProductDto getProductById(String id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProductById(String id) throws NotFoundException;
    GenericProductDto updateProductById(String id, GenericProductDto product) throws NotFoundException;
}
