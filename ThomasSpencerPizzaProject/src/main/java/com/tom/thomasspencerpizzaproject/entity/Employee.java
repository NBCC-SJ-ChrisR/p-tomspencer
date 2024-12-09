package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id") // Map to the "customer_id" column
    private Integer employeeId;

    private String username;
    private String password;
}