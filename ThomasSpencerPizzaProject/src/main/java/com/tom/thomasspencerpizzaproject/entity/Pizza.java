package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="pizza_id")
    private Integer pizzaId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "pizzaSize_id")
    private PizzaSize pizzaSize;

    @ManyToOne
    @JoinColumn(name = "pizzaCrust_id")
    private PizzaCrust pizzaCrust;

    private Integer quantity;
    private float priceEach;
    private float totalPrice;

    // Getters and Setters
}
