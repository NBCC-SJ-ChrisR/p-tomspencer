package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for performing database operations on the Customer entity.
 *
 * This interface extends JpaRepository, providing standard CRUD operations and
 * custom query methods for the Customer entity. Spring Data JPA automatically
 * implements this interface at runtime.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    /**
     * Finds a customer by their email address.
     *
     * This method uses Spring Data JPA's query derivation feature to generate
     * a query based on the method name. It returns an Optional containing the
     * matching customer if found, or an empty Optional if no match is found.
     *
     * @param email the email address to search for
     * @return an Optional containing the Customer if found, or empty if not
     */
    Optional<Customer> findByEmail(String email);
}