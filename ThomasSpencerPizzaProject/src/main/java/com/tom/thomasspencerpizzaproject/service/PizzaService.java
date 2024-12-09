package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.repository.PizzaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pr;

    public PizzaService(PizzaRepository pr) {
        this.pr = pr;
    }

    public List<Pizza> getAllPizzas() {
        return pr.findAll();
    }

    public Pizza getPizzaById(int id) {
        Optional<Pizza> pizza = pr.findById(id);
        if (pizza.isPresent()) {
            return pizza.get();
        } else {
            throw new RuntimeException("Pizza with ID " + id + " not found.");
        }
    }

    public Pizza savePizza(Pizza pizza) {
        try {
            return pr.save(pizza);
        } catch (DataIntegrityViolationException e) {
            String specificMessage = getRootCauseMessage(e);
            throw new DataIntegrityViolationException(specificMessage, e);
        }
    }

    public boolean deletePizzaById(int id) {
        try {
            if (pr.existsById(id)) { // Check if the pizza exists
                pr.deleteById(id);  // Delete the pizza
                return true;        // Return true if successful
            }
            return false;           // Return false if the pizza doesn't exist
        } catch (DataIntegrityViolationException e) {
            String specificMessage = getRootCauseMessage(e);
            throw new DataIntegrityViolationException(specificMessage, e);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting pizza", e);
        }
    }

    // Helper method to extract root cause message
    private String getRootCauseMessage(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause != rootCause.getCause()) {
            rootCause = rootCause.getCause();
        }
        return rootCause.getMessage();
    }
}