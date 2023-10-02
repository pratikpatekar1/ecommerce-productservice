package dev.zoro.productservice.inheritancedemo.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name = "msc_student")
public class Student extends User {
    private double psp;
}
