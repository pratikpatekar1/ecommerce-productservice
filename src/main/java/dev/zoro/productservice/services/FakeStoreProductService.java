package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.FakeStoreProductDto;
import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private String specificProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String productRequestBaseUrl = "https://fakestoreapi.com/products";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    private GenericProductDto convertFakeStoreProductToGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }
    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null)throw new NotFoundException("Product with id: "+id+" does not exist.");
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }
    public GenericProductDto createProduct(GenericProductDto product){
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl,product, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }

    public List<GenericProductDto> getAllProducts(){
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<GenericProductDto>result = new ArrayList();
        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
            result.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto));
        }
        return result;
    }

    public GenericProductDto deleteProductById(Long id){
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(specificProductRequestUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();;
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }
    public GenericProductDto updateProductById(Long id, GenericProductDto product){
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product); // Can also add Headers as 2nd argument if required
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(specificProductRequestUrl, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class , id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductToGenericProduct(fakeStoreProductDto);
    }
    //TODO: PATCH
}
