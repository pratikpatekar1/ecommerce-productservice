package dev.zoro.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User {
    private double averageRating;
}
