package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.Employee;
import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import com.tom.thomasspencerpizzaproject.service.EmployeeService;
import com.tom.thomasspencerpizzaproject.service.PizzaToppingService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaToppings")
public class PizzaToppingController {
    private final PizzaToppingService pizzaToppingService;

    public PizzaToppingController(PizzaToppingService pts) {
        this.pizzaToppingService = pts;
    }

    @GetMapping
    public ResponseEntity<?> getAllPizzaToppings() {
        try {
            List<PizzaTopping> pizzaToppings = pizzaToppingService.getAllPizzaToppings();
            return ResponseEntity.ok(pizzaToppings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPizzaTopping(@PathVariable int id) {
        try {
            PizzaTopping pizzaTopping = pizzaToppingService.getPizzaToppingById(id);
            if (pizzaTopping != null) {
                return ResponseEntity.ok(pizzaTopping);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPizzaTopping(@RequestBody PizzaTopping pizzaTopping) {
        try {
            System.out.println("Post req for employee: " + pizzaTopping);
            System.out.println("Received isActive: " + pizzaTopping.isActive());

            PizzaTopping savedPizzaTopping = pizzaToppingService.savePizzaTopping(pizzaTopping);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPizzaTopping); // Return 201 Created
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Return 409 Conflict with error message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while creating the employee.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePizzaTopping(@PathVariable int id, @RequestBody PizzaTopping pizzaTopping) {
        try {
            System.out.println("Put req for employee: " + pizzaTopping);
            pizzaTopping.setPizzaToppingId(id); // Ensure the ID is set
            PizzaTopping updatedPizzaTopping = pizzaToppingService.savePizzaTopping(pizzaTopping);
            return ResponseEntity.ok(updatedPizzaTopping); // Return 200 OK with updated employee
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Return 409 Conflict with error message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while updating the pizza topping.");
        }
    }
}
