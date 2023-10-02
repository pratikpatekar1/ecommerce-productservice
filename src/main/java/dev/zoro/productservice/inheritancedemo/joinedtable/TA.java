package dev.zoro.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;

@Entity(name = "jt_ta")
public class TA extends User{
    private double averageRating;
}
