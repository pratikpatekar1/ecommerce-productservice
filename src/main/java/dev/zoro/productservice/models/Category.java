package dev.zoro.productservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
//import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Entity
@Getter
@Setter
//@Document(indexName = "productservice")
public class Category extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "category")
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore
    private List<Product> products;
}
