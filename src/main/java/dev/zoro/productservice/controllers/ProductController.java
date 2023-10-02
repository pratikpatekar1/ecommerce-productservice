package dev.zoro.productservice.controllers;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(("/products"))
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto product){
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@PathVariable("id") UUID id, @RequestBody GenericProductDto product){
        return new ResponseEntity<>(productService.updateProductById(id, product),HttpStatus.OK);
    }
    //TODO: PATCH
}
