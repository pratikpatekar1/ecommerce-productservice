package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.CategoryDto;
import dev.zoro.productservice.dtos.CategoryResponseDto;
import dev.zoro.productservice.dtos.ProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.repositories.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category getProductsForCategory(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));
        if(categoryOptional.isEmpty())return null;
//        List<Product> products = categoryOptional.get().getProducts();
        return categoryOptional.get();
    }

    public List<CategoryResponseDto> getAllCategories() throws NotFoundException {
        List<Category> categories = categoryRepository.findAll();
        if(categories==null)throw new NotFoundException("No categories found.");
        List<CategoryResponseDto> categoryDtos = new ArrayList<>();
        for(Category category: categories){
            CategoryResponseDto categoryresponseDto = new CategoryResponseDto();
            categoryresponseDto.setName(category.getName());
            categoryresponseDto.setUuid(category.getId());
            categoryDtos.add(categoryresponseDto);
        }
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> getACategory(String categoryName) throws NotFoundException {
        Optional<List<Category>> categoryOptional = categoryRepository.findByName(categoryName);

        if(categoryOptional.isEmpty())throw new NotFoundException("Category with name: "+categoryName+" does not exist.");

        List<Category> categories = categoryOptional.get();

        List<CategoryDto> categoryDtos = new ArrayList<>();

        for(Category category:categories){
            List<Product> products = category.getProducts();
            List<ProductDto> productDtos = new ArrayList<>();
            for(Product product:products){
                ProductDto productDto = new ProductDto();
                productDto.setImage(product.getImage());
                productDto.setDescription(product.getDescription());
                productDto.setTitle(product.getTitle());
                productDto.setPrice(product.getPrice());
                productDtos.add(productDto);
            }
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(category.getName());
            categoryDto.setProducts(productDtos);
            categoryDto.setUuid(category.getId());
            categoryDtos.add(categoryDto);
        }

        return categoryDtos;
    }
}

