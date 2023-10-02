package dev.zoro.productservice.inheritancedemo.singletable;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {
    private double psp;
}
