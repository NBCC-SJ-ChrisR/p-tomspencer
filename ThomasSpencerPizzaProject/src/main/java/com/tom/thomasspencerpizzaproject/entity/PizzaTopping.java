package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data

public class PizzaTopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizzaTopping_id") // Map to the "customer_id" column
    private Integer pizzaToppingId;

    private String name;
    private float price;

    private Timestamp createDate;

    private Integer empAddedBy;

    @Column(name = "isActive")
    private boolean isActive;

}