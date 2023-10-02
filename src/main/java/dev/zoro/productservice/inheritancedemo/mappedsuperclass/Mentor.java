package dev.zoro.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_mentor")
public class Mentor extends User {
    private double averageRating;
}
