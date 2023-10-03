package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.CategoryDto;
import dev.zoro.productservice.dtos.CategoryResponseDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category getProductsForCategory(String id);
    List<CategoryResponseDto> getAllCategories() throws NotFoundException;

    List<CategoryDto> getACategory(String categoryName) throws NotFoundException;
}
