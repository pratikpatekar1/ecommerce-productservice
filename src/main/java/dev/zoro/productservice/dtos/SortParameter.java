package dev.zoro.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParameter {
    private String parameterName;
    private String sortType; // ASC or DESC
}
