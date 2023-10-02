package dev.zoro.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_ta")
public class TA extends User {
    private double averageRating;
}
