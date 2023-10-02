package dev.zoro.productservice.inheritancedemo.singletable;

import jakarta.persistence.Entity;

@Entity
public class Mentor extends User {
    private double averageRating;
}
