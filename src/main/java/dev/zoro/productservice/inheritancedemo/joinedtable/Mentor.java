package dev.zoro.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;

@Entity(name = "jt_mentor")
public class Mentor extends User{
    private double averageRating;
}
