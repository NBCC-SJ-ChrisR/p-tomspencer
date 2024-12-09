package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data

public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id") // Map to the "customer_id" column
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private float subTotal;
    private float hst;
    private float orderTotal;
    private boolean delivery;

    private String orderStatus;

    private LocalDateTime deliveryDate;

    private LocalDateTime orderPlacedDate;

    // Getters and Setters
}
