package dev.zoro.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_mentor")
public class Mentor extends User {
    private double averageRating;
}
