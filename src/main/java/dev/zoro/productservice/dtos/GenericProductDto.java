package dev.zoro.productservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Price;
import dev.zoro.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
public class GenericProductDto implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public static GenericProductDto from(Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setCategory(product.getCategory().getName());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setPrice(product.getPrice().getPrice());
        return genericProductDto;
    }
}
