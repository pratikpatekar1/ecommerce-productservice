package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.dtos.UserDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Price;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.repositories.CategoryRepository;
import dev.zoro.productservice.repositories.ProductSearchRepository;
import dev.zoro.productservice.repositories.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Primary
@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductSearchRepository productSearchRepository;
    private RestTemplate restTemplate;

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductSearchRepository productSearchRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productSearchRepository = productSearchRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public GenericProductDto getProductById(String id) throws NotFoundException {
        //TODO: check if userID is authorized to access the product
        ResponseEntity<UserDto> userDto =  restTemplate.getForEntity("http://userservice/users/0x205F18507C0C42B1BDC544A58E6479B0", UserDto.class);

        Optional<Product> productOptional = productRepository.findProductById(UUID.fromString(id));
        if (productOptional.isEmpty()) throw new NotFoundException("Product with id: " + id + " does not exist.");
        return GenericProductDto.from(productOptional.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        Product product1 = new Product();

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

        product1.setId(savedProduct.getId());
        productSearchRepository.save(product1);

        return GenericProductDto.from(product1);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (Product product : products) {
            genericProductDtos.add(GenericProductDto.from(product));
        }
        return genericProductDtos;
    }
    @Override
    public GenericProductDto deleteProductById(String id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findProductById(UUID.fromString(id));
        if(productOptional.isEmpty()) throw new NotFoundException("Product with id: "+id+" does not exist.");
        productRepository.deleteById(UUID.fromString(id));
        productSearchRepository.deleteById(UUID.fromString(id));
        return GenericProductDto.from(productOptional.get());
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto product) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findProductById(UUID.fromString(id));
        Product elasticProduct = productSearchRepository.findById(UUID.fromString(id)).get();

        if(productOptional.isEmpty())throw new NotFoundException("Product with id: "+id+" does not exist.");

        Product product1 = productOptional.get();

        product1.setTitle(product.getTitle());
        product1.setDescription(product.getDescription());
        product1.setImage(product.getImage());

        elasticProduct.setTitle(product.getTitle());
        elasticProduct.setDescription(product.getDescription());
        elasticProduct.setImage(product.getImage());

        Category category = categoryRepository.getCateogryByName(product.getCategory());
        if(category==null){
            category = new Category();
            category.setName(product.getCategory());
        }

        Price price = product1.getPrice();
        price.setPrice(product.getPrice());
        product1.setCategory(category);
        product1.setPrice(price);

        elasticProduct.setCategory(category);
        elasticProduct.setPrice(price);

        Product savedProduct = productRepository.save(product1);

        productSearchRepository.save(elasticProduct);
        return GenericProductDto.from(savedProduct);
    }

}
