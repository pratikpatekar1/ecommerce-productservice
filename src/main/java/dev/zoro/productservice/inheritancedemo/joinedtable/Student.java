package dev.zoro.productservice.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;

@Entity(name = "jt_student")
public class Student extends User {
    private double psp;
}
