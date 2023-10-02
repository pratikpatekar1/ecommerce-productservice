package dev.zoro.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_student")
public class Student extends User {
    private double psp;
}
