package dev.zoro.productservice.dtos;

import dev.zoro.productservice.models.Category;
import dev.zoro.productservice.models.Price;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String title;
    private String description;
    private String image;
    private Price price;
}
