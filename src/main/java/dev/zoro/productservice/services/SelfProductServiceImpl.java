package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Price;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Primary
@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

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

    public SelfProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        return convertToGenericProductDto(productRepository.findProductById(id));
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Product product1 = new Product();
        product1.setId(product.getId());
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        Category category = new Category();
        category.setName(product.getCategory());
        product1.setCategory(category);
        product1.setImage(product.getImage());
        Price price = new Price();
        price.setPrice(product.getPrice());
        price.setCurrency("INR");
        product1.setPrice(price);
//        product1.setPrice(product.getPrice());
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
    public GenericProductDto deleteProductById(UUID id) throws NotFoundException {
        Product product = productRepository.findProductById(id);
        if( product == null) throw new NotFoundException("Product with id: "+id+" does not exist.");
        productRepository.deleteById(id);
        return convertToGenericProductDto(product);
    }

    @Override
    public GenericProductDto updateProductById(UUID id, GenericProductDto product) {
        Product product1 = productRepository.findProductById(id);
        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());
        Category category = product1.getCategory();
        category.setName(product.getCategory());
        Price price = product1.getPrice();
        price.setPrice(product.getPrice());
        product1.setCategory(category);
        product1.setPrice(price);
        Product savedProduct = productRepository.save(product1);
        return convertToGenericProductDto(savedProduct);
    }
}
