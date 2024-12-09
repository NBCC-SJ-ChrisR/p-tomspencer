package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Customer;
import com.tom.thomasspencerpizzaproject.repository.CustomerRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing Customer operations.
 *
 * This class provides methods for handling customer-related operations, including
 * retrieving, saving, and deleting customers. It acts as an intermediary between
 * the controller layer and the repository layer, encapsulating business logic.
 */
@Service
public class CustomerService {

    /**
     * Repository for accessing the customer data.
     */
    private final CustomerRepository cr;

    /**
     * Constructor for dependency injection of CustomerRepository.
     *
     * @param cr the customer repository
     */
    public CustomerService(CustomerRepository cr) {
        this.cr = cr;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of all customers in the database
     */
    public List<Customer> getAllCustomers() {
        return cr.findAll();
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the ID of the customer to retrieve
     * @return the customer with the specified ID
     * @throws NoSuchElementException if no customer is found with the given ID
     */
    public Customer getCustomerById(int id) {
        return cr.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Customer with ID " + id + " not found."));
    }

    /**
     * Retrieves a customer by their email address.
     *
     * @param email the email address of the customer to retrieve
     * @return the customer with the specified email address
     * @throws IllegalArgumentException if no customer is found with the given email
     */
    public Customer findByEmail(String email) {
        return cr.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Customer with email " + email + " not found."));
    }

    /**
     * Saves or updates a customer in the database.
     *
     * @param customer the customer to save or update
     * @return the saved or updated customer
     * @throws DataIntegrityViolationException if a database constraint is violated
     */
    public Customer saveCustomer(Customer customer) {
        try {
            return cr.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     * @throws DataIntegrityViolationException if a database constraint is violated
     */
    public void deleteCustomerById(int id) {
        try {
            cr.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        } catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}