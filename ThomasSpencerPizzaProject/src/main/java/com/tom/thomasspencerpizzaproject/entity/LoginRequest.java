package com.tom.thomasspencerpizzaproject.entity;

/**
 * DTO (Data Transfer Object) class for handling login requests.
 *
 * This class is used to encapsulate login credentials for both customers
 * and employees. It includes fields for email (customer login), username
 * (employee login), and password. The appropriate field is used depending
 * on the type of login.
 */
public class LoginRequest {

    /**
     * The email address for customer login.
     * This field is used exclusively for customer login requests.
     */
    private String email;

    /**
     * The username for employee login.
     * This field is used exclusively for employee login requests.
     */
    private String username;

    /**
     * The password for authentication.
     * Used in both customer and employee login requests.
     */
    private String password;

    // Getters and setters

    /**
     * Retrieves the email address for customer login.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address for customer login.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the username for employee login.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for employee login.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password for authentication.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for authentication.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}