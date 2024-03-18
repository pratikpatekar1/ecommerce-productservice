package dev.zoro.productservice.thirdpartyclients.productservice.fakestore;

import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FakeStoreProductServiceClient {
    private RestTemplate restTemplate;
    private String specificProductRequestUrl;
    private String productRequestBaseUrl;
    public FakeStoreProductServiceClient(RestTemplate restTemplate, @Value("${fakestore.api.url}") String fakeStoreUrl, @Value("${fakestore.api.productendpoint}") String fakeStoreProductEndpoint){
        this.restTemplate = restTemplate;
        this.productRequestBaseUrl = fakeStoreUrl+fakeStoreProductEndpoint;
        this.specificProductRequestUrl = fakeStoreUrl+fakeStoreProductEndpoint+"/{id}";
    }
    public FakeStoreProductDto getProductById(String id) throws NotFoundException {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        if(fakeStoreProductDto==null)throw new NotFoundException("Product with id: "+id+" does not exist.");
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto createProduct(GenericProductDto product){
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestBaseUrl,product, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts(){
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);
        List<FakeStoreProductDto>result = new ArrayList();
        for(FakeStoreProductDto fakeStoreProductDto: Arrays.stream(response.getBody()).toList()){
            result.add(fakeStoreProductDto);
        }
        return result;
    }

    public FakeStoreProductDto deleteProductById(String id){
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(specificProductRequestUrl, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();;
        return fakeStoreProductDto;
    }
    public FakeStoreProductDto updateProductById(String id, GenericProductDto product) throws NotFoundException {
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product); // Can also add Headers as 2nd argument if required
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(specificProductRequestUrl, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class , id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }
    //TODO: PATCH
}
