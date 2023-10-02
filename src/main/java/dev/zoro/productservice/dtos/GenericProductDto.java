package dev.zoro.productservice.dtos;

import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class GenericProductDto {
    private UUID id;
    private String title;
    private String description;
    private String image;
    private Category category;
    private Price price;
}
