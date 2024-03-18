package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.models.Product;
import dev.zoro.productservice.repositories.ProductSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
//    private ProductRepository productRepository;
    private ProductSearchRepository productSearchRepository;
    public SearchService(ProductSearchRepository productSearchRepository) {
        this.productSearchRepository = productSearchRepository;
    }
//    public Page<GenericProductDto>searchProducts(String query, Pageable pageable){
//        Code for searching using ProductRepository

//        Page<Product> productPage = productRepository.findAllByTitleContaining(query, pageable);
//        List<Product> products = productPage.getContent();
//        List<GenericProductDto> genericProductDtos = new ArrayList<>();
//        for(Product product: products){
//            genericProductDtos.add(GenericProductDto.from(product));
//        }
//        Page<GenericProductDto> genericProductsPage = new PageImpl<>(genericProductDtos, productPage.getPageable(), productPage.getTotalPages());
//        return genericProductsPage;

        //Code for searching using ProductElasticSearchRepository

//    }

    public Page<GenericProductDto> searchProducts(String query, Pageable pageable){
        Page<Product> productsPage = productSearchRepository.findAllByTitleContainingOrDescriptionContaining(query, pageable);

        List<Product> products = productsPage.getContent();
        List<GenericProductDto> genericProductDtos = new ArrayList<>();

        for(Product product: products){
            genericProductDtos.add(GenericProductDto.from(product));
        }

        Page<GenericProductDto> productPage = new PageImpl<>(genericProductDtos, productsPage.getPageable(), productsPage.getTotalPages());
        return productPage;
    }


}
