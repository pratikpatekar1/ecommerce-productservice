package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.FakeStoreProductDto;
import dev.zoro.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl = "https://fakestoreapi.com/products";
    private String deleteProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String updateProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        // long way -> mapping individual response body data fields to dto fields
//        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl, FakeStoreProductDto.class, id);
//        FakeStoreProductDto fakeStoreProductDto = response.getBody();
//        GenericProductDto product = new GenericProductDto();
//        product.setId(fakeStoreProductDto.getId());
//        product.setImage(fakeStoreProductDto.getImage());
//        product.setDescription(fakeStoreProductDto.getDescription());
//        product.setTitle(fakeStoreProductDto.getTitle());
//        product.setCategory(fakeStoreProductDto.getCategory());
//        product.setPrice(fakeStoreProductDto.getPrice());
//        return product;

        // short way, internal mapping
        ResponseEntity<GenericProductDto> response = restTemplate.getForEntity(getProductRequestUrl, GenericProductDto.class, id);
        return response.getBody();
    }
    public GenericProductDto createProduct(GenericProductDto product){
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestUrl,product, GenericProductDto.class);
        return response.getBody();
    }

    public GenericProductDto[] getAllProducts(){
        ResponseEntity<GenericProductDto[]> response = restTemplate.getForEntity(getAllProductsRequestUrl,GenericProductDto[].class);
        return response.getBody();
    }

    public GenericProductDto deleteProductById(Long id){
        // without getting response body
//        restTemplate.delete(deleteProductRequestUrl, id);
//        return "Deleted the resource with id: "+id;

        // with response body
        ResponseEntity<GenericProductDto> response = restTemplate.exchange(deleteProductRequestUrl, HttpMethod.DELETE, null, GenericProductDto.class, id);
        return response.getBody();
    }
    public GenericProductDto updateProductById(Long id, GenericProductDto product){
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product); // Can also add Headers as 2nd argument if required
        ResponseEntity<GenericProductDto> response = restTemplate.exchange(updateProductRequestUrl, HttpMethod.PUT, requestEntity, GenericProductDto.class , id);
        return response.getBody();
    }
}
