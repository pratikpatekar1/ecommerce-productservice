package dev.zoro.productservice.inheritancedemo.singletable;

import jakarta.persistence.Entity;

@Entity
public class TA extends User {
    private double averageRating;
}
