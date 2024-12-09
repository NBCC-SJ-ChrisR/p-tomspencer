package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "pizzaTopping_map")
public class PizzaToppingMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pizzaTopping_map_id")
    private Integer pizzaToppingMapId;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @ManyToOne
    @JoinColumn(name = "pizzaTopping_id")
    private PizzaTopping pizzaTopping;

    // Getters and Setters
}
