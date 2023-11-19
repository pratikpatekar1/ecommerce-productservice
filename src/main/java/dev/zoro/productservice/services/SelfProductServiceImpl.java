package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Price;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.repositories.CategoryRepository;
import dev.zoro.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    private GenericProductDto convertToGenericProductDto(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getPrice());
        return genericProductDto;
    }

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        //TODO: check if userID is authorized to access the product

        Optional<Product> productOptional = productRepository.findProductById(UUID.fromString(id));
        if (productOptional.isEmpty()) throw new NotFoundException("Product with id: " + id + " does not exist.");
        return convertToGenericProductDto(productOptional.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Product product1 = new Product();

        product1.setId(product.getId());
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());

        Category category = categoryRepository.getCateogryByName(product.getCategory());
        if(category==null){
            category = new Category();
            category.setName(product.getCategory());
        }

        product1.setCategory(category);
        product1.setImage(product.getImage());

        Price price = new Price();
        price.setPrice(product.getPrice());
        price.setCurrency("INR");

        product1.setPrice(price);
        Product savedProduct = productRepository.save(product1);

        return convertToGenericProductDto(savedProduct);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product : products) {
            genericProductDtos.add(convertToGenericProductDto(product));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductById(String id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findProductById(UUID.fromString(id));
        if(productOptional.isEmpty()) throw new NotFoundException("Product with id: "+id+" does not exist.");
        productRepository.deleteById(UUID.fromString(id));
        return convertToGenericProductDto(productOptional.get());
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto product) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findProductById(UUID.fromString(id));
        if(productOptional.isEmpty())throw new NotFoundException("Product with id: "+id+" does not exist.");
        Product product1 = productOptional.get();
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        Category category = categoryRepository.getCateogryByName(product.getCategory());
        if(category==null){
            category = new Category();
            category.setName(product.getCategory());
        }
        Price price = product1.getPrice();
        price.setPrice(product.getPrice());
        product1.setCategory(category);
        product1.setPrice(price);
        Product savedProduct = productRepository.save(product1);
        return convertToGenericProductDto(savedProduct);
    }

}
