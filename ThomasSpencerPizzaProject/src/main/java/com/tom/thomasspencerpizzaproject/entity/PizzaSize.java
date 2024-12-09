package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class PizzaSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="pizzaSize_id")
    private Integer pizzaSizeId;

    private String name;
    private float price;

}