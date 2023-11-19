package dev.zoro.productservice.controllers;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.exceptions.UnauthorizedException;
import dev.zoro.productservice.security.JwtObject;
import dev.zoro.productservice.security.TokenValidator;
import dev.zoro.productservice.services.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("/products"))
public class ProductController {
    private ProductService productService;
    private TokenValidator tokenValidator;
    public ProductController(ProductService productService, TokenValidator tokenValidator){
        this.productService = productService;
        this.tokenValidator = tokenValidator;
    }
    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts(){
        List<GenericProductDto>productDtos = productService.getAllProducts();
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") String id) throws NotFoundException, UnauthorizedException {
//        Optional<JwtObject> authTokenObjOptional;
//        JwtObject authTokenObj;
//
//        if(authToken==null)throw new UnauthorizedException("Unauthorized to access the resource");
//
//        authTokenObjOptional = tokenValidator.validateToken(authToken);
//        if(authTokenObjOptional.isEmpty())throw new UnauthorizedException("Unauthorized to access the resource");
//
//        authTokenObj = authTokenObjOptional.get();
//
//        return new ResponseEntity<>(productService.getProductById(id,authTokenObj.getId()),HttpStatus.OK);
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GenericProductDto> createProduct(@RequestBody GenericProductDto product){
        GenericProductDto productDto = productService.createProduct(product);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<GenericProductDto> updateProductById(@PathVariable("id") String id, @RequestBody GenericProductDto product) throws NotFoundException{
        return new ResponseEntity<>(productService.updateProductById(id, product),HttpStatus.OK);
    }
}
