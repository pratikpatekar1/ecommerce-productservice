package dev.zoro.productservice.controllers;

import dev.zoro.productservice.dtos.CategoryDto;
import dev.zoro.productservice.dtos.CategoryResponseDto;
import dev.zoro.productservice.dtos.ProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(@Qualifier("categoryServiceImpl") CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @GetMapping("/id/{uuid}")
    public ResponseEntity<List<ProductDto>> getProductsForCategory(@PathVariable("uuid") String uuid) {
        Category category = categoryService.getProductsForCategory(uuid);

        List<Product> products = category.getProducts();

        List<ProductDto>productDtos = new ArrayList<>();

        for(Product product:products){
            ProductDto productDto = new ProductDto();
            productDto.setImage(product.getImage());
            productDto.setDescription(product.getDescription());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDtos.add(productDto);
        }
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() throws NotFoundException {
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<CategoryDto>> getACategory(@PathVariable("categoryName") String categoryName) throws NotFoundException{
        return new ResponseEntity<>(categoryService.getACategory(categoryName),HttpStatus.OK);
    }
}
