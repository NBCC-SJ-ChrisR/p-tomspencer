package com.tom.thomasspencerpizzaproject.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity class representing a Customer.
 *
 * This class maps to the "customer" table in the database and defines all
 * relevant fields along with their mappings. It uses Lombok to generate
 * boilerplate code like getters, setters, and `toString` methods.
 */
@Entity
@Data
@Table(name = "customer") // Map the entity to the "customer" table
public class Customer {

    /**
     * The primary key of the customer table.
     *
     * This field is auto-generated using the IDENTITY strategy.
     * The IDENTITY strategy in JPA specifies that the database will
     * automatically generate and manage the primary key value, typically
     * using an auto-increment column.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id") // Map to the "customer_id" column
    private Integer customerId;

    /**
     * The first name of the customer.
     */
    @Column(name = "firstName") // Map to "firstName" column in the database
    private String firstName;

    /**
     * The last name of the customer.
     */
    @Column(name = "lastName") // Map to "lastName" column in the database
    private String lastName;

    /**
     * The phone number of the customer.
     */
    @Column(name = "phoneNumber") // Map to "phoneNumber" column in the database
    private String phoneNumber;

    /**
     * The email address of the customer.
     *
     * This field is expected to be unique in the database to avoid duplicate registrations.
     */
    @Column(name = "email") // Map to "email" column in the database
    private String email;

    /**
     * The hashed password of the customer.
     *
     * Passwords should always be stored securely in hashed form.
     */
    @Column(name = "password") // Map to "password" column in the database
    private String password;

    /**
     * The house number of the customer's address.
     */
    @Column(name = "houseNumber") // Map to "houseNumber" column in the database
    private Integer houseNumber;

    /**
     * The street name of the customer's address.
     */
    @Column(name = "street") // Map to "street" column in the database
    private String street;

    /**
     * The province of the customer's address.
     */
    @Column(name = "province") // Map to "province" column in the database
    private String province;

    /**
     * The postal code of the customer's address.
     */
    @Column(name = "postalCode") // Map to "postalCode" column in the database
    private String postalCode;
}