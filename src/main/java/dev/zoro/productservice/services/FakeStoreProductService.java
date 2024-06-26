package dev.zoro.productservice.services;

import dev.zoro.productservice.dtos.CategoryDto;
import dev.zoro.productservice.dtos.CategoryResponseDto;
import dev.zoro.productservice.dtos.GenericProductDto;
import dev.zoro.productservice.exceptions.NotFoundException;
import dev.zoro.productservice.security.JwtObject;
import dev.zoro.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.zoro.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    private RedisTemplate<String, Object> redisTemplate;
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
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient, RedisTemplate<String, Object> redisTemplate){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
        this.redisTemplate = redisTemplate;
    }


    public GenericProductDto getProductById(String id) throws NotFoundException {
        // check if key is already present in redis
        GenericProductDto genericProductDto = (GenericProductDto) redisTemplate.opsForHash().get("PRODUCTS",id);
        if(genericProductDto!=null){
            return genericProductDto;
        }
        GenericProductDto genericProductDto1 = convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.getProductById(id));
        redisTemplate.opsForHash().put("PRODUCTS",id,genericProductDto1);
        return genericProductDto1;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDto =  fakeStoreProductServiceClient.getAllProducts();
        List<GenericProductDto>result = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto1: fakeStoreProductDto){
            result.add(convertFakeStoreProductToGenericProduct(fakeStoreProductDto1));
        }
        return result;
    }

    @Override
    public GenericProductDto deleteProductById(String id) {
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
    }

    @Override
    public GenericProductDto updateProductById(String id, GenericProductDto product) throws NotFoundException {
        return convertFakeStoreProductToGenericProduct(fakeStoreProductServiceClient.updateProductById(id,product));
    }
}
