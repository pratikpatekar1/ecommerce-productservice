package dev.zoro.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDto {
    private String name;
    private UUID uuid;
}
