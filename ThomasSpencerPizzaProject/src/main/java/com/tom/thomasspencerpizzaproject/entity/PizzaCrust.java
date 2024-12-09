package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class PizzaCrust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizzaCrust_id")
    private Integer pizzaCrustId;

    private String name;
    private float price;

}