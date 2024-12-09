package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import com.tom.thomasspencerpizzaproject.entity.PizzaToppingMap;
import com.tom.thomasspencerpizzaproject.service.PizzaToppingMapService;
import com.tom.thomasspencerpizzaproject.service.PizzaToppingService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pizzaToppingMap")
public class PizzaToppingMapController {
    private final PizzaToppingMapService pizzaToppingMapService;

    public PizzaToppingMapController(PizzaToppingMapService ptms) {
        this.pizzaToppingMapService = ptms;
    }

    @GetMapping
    public ResponseEntity<?> getAllPizzaToppings() {
        try {
            List<PizzaToppingMap> pizzaToppingMaps = pizzaToppingMapService.getAllPizzaToppingMaps();
            return ResponseEntity.ok(pizzaToppingMaps);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPizzaTopping(@PathVariable int id) {
        try {
            PizzaToppingMap pizzaToppingMap = pizzaToppingMapService.getPizzaToppingMapById(id);
            if (pizzaToppingMap != null) {
                return ResponseEntity.ok(pizzaToppingMap);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createPizzaToppingMap(@RequestBody PizzaToppingMap pizzaToppingMap) {
        try {
            System.out.println("Post req for pizza topping map: " + pizzaToppingMap);
            PizzaToppingMap savedPizzaToppingMap = pizzaToppingMapService.savePizzaToppingMap(pizzaToppingMap);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPizzaToppingMap); // Return 201 Created
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Return 409 Conflict with error message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while creating the employee.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePizzaToppingMap(@PathVariable int id) {
        try {
            boolean isDeleted = pizzaToppingMapService.deletePizzaToppingMapById(id);
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
}
