package dev.zoro.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import static jakarta.persistence.FetchType.LAZY;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category")
    private Category category;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;
}
