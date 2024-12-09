package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.Customer;
import com.tom.thomasspencerpizzaproject.entity.LoginRequest;
import com.tom.thomasspencerpizzaproject.service.CustomerService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing customer-related operations.
 * Provides endpoints for creating, retrieving, updating customers, and handling login.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    /**
     * Constructor for dependency injection of the CustomerService.
     *
     * @param cs the customer service instance
     */
    public CustomerController(CustomerService cs) {
        this.customerService = cs;
    }

    /**
     * Retrieves all customers.
     *
     * @return ResponseEntity containing the list of customers or an error message.
     */
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Retrieves a specific customer by ID.
     *
     * @param id the ID of the customer to retrieve
     * @return ResponseEntity containing the customer object or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                return ResponseEntity.ok(customer);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Updates an existing customer's details.
     *
     * @param id       the ID of the customer to update
     * @param customer the updated customer details
     * @return ResponseEntity containing the updated customer or an error message.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        try {
            System.out.println("Put req for customer: " + customer);

            // Retrieve the existing customer from the database
            Customer existingCustomer = customerService.getCustomerById(id);
            if (existingCustomer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Customer with ID " + id + " not found.");
            }

            // If the password is null in the request, retain the existing password
            if (customer.getPassword() == null) {
                customer.setPassword(existingCustomer.getPassword());
            } else if (customer.getPassword().length() < 6) {
                // Validate password length if a new password is provided
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Password must be at least 6 characters long.");
            } else {
                // Hash the password if it is being updated
                customer.setPassword(HashUtil.hashPassword(customer.getPassword()));
            }

            // Ensure the ID is correctly set
            customer.setCustomerId(id);

            // Save the updated customer
            Customer updatedCustomer = customerService.saveCustomer(customer);

            return ResponseEntity.ok(updatedCustomer); // Return 200 OK with updated customer
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Handle database constraint violations
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while updating the customer.");
        }
    }
    /**
     * Creates a new customer.
     *
     * @param customer the customer details to create
     * @return ResponseEntity containing the created customer or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            System.out.println("Post req for customer: " + customer);

            // Validate password length
            if (customer.getPassword() == null || customer.getPassword().length() < 6) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Password must be at least 6 characters long.");
            }

            // Hash the password before saving
            customer.setPassword(HashUtil.hashPassword(customer.getPassword()));
            Customer savedCustomer = customerService.saveCustomer(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(customer); // Return 201 Created
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Handle database constraint violations
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while creating the customer.");
        }
    }

    /**
     * Handles customer login.
     *
     * @param loginRequest the login request containing email and password
     * @return ResponseEntity containing the customer object if login is successful or an error message.
     */
    @PostMapping("/login")
    public ResponseEntity<?> customerLogin(@RequestBody LoginRequest loginRequest) {
        try {
            Customer customer = customerService.findByEmail(loginRequest.getEmail());

            // Verify that the customer exists and the password matches
            if (customer == null || !new BCryptPasswordEncoder().matches(loginRequest.getPassword(), customer.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
            }

            // Password matches, return customer details
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred during login.");
        }
    }
}