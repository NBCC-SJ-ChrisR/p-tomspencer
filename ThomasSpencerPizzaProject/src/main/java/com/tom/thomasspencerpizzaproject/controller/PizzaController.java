package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.service.PizzaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * REST Controller for managing pizza-related operations.
 * Provides endpoints for creating, retrieving, and deleting pizzas.
 */
@RestController
@RequestMapping("/pizzas")
public class PizzaController {
    private final PizzaService pizzaService;

    /**
     * Constructor for dependency injection of the PizzaService.
     *
     * @param ps the PizzaService instance
     */
    public PizzaController(PizzaService ps) {
        this.pizzaService = ps;
    }

    /**
     * Retrieves all pizzas.
     *
     * @return ResponseEntity containing the list of pizzas or an error message.
     */
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        try {
            List<Pizza> pizzas = pizzaService.getAllPizzas();
            return ResponseEntity.ok(pizzas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Retrieves a specific pizza by ID.
     *
     * @param id the ID of the pizza to retrieve
     * @return ResponseEntity containing the pizza object or a 404 Not Found status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPizza(@PathVariable int id) {
        try {
            Pizza pizza = pizzaService.getPizzaById(id);
            if (pizza != null) {
                return ResponseEntity.ok(pizza);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Creates a new pizza.
     *
     * Handles specific database errors (e.g., constraint violations) by parsing
     * exception messages for meaningful feedback to the client.
     *
     * @param pizza the pizza object to create
     * @return ResponseEntity containing the created pizza or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createPizza(@RequestBody Pizza pizza) {
        try {
            System.out.println("Post req for pizza: " + pizza);
            Pizza savedPizza = pizzaService.savePizza(pizza);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPizza); // Return 201 Created
        } catch (DataIntegrityViolationException e) {
            // Extract a meaningful message from the exception
            String fullMessage = e.getMostSpecificCause().getMessage();
            String extractedMessage = extractMessageFromException(fullMessage,
                    "An unexpected error occurred while creating the pizza.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(extractedMessage);
        } catch (Exception e) {
            // Handle generic exceptions
            String fullMessage = e.getMessage();
            String extractedMessage = extractMessageFromException(fullMessage,
                    "An unexpected error occurred while creating the pizza.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(extractedMessage);
        }
    }

    /**
     * Deletes a pizza by its ID.
     *
     * @param id the ID of the pizza to delete
     * @return ResponseEntity with appropriate HTTP status based on the result.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePizza(@PathVariable int id) {
        try {
            boolean isDeleted = pizzaService.deletePizzaById(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build(); // Return 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // Return 404 Not Found if pizza doesn't exist
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the pizza.\n" + e.getMessage());
        }
    }

    /**
     * Utility method to extract a meaningful error message from an exception.
     *
     * Parses messages for content within square brackets. If no match is found, returns
     * a fallback message. The SQL Exception from the trigger comes in tandem with Spring's
     * error message, so we can separate the two messages using Regex.
     *
     * @param fullMessage    the full exception message
     * @param fallbackMessage the fallback message if no content is extracted
     * @return the extracted or fallback message
     */
    private String extractMessageFromException(String fullMessage, String fallbackMessage) {
        // Regular expression to extract content within square brackets
        Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
        Matcher matcher = pattern.matcher(fullMessage);

        if (matcher.find()) {
            return matcher.group(1); // Extract content within the first set of brackets
        } else {
            return fallbackMessage; // Use the fallback message if no match is found
        }
    }
}