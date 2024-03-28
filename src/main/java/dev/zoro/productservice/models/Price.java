package dev.zoro.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Entity
//@Document(indexName = "productservice")
public class Price extends BaseModel {
    private double price = 0;
    private String currency;
}
