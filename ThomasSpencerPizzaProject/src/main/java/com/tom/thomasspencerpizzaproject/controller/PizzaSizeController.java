package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.entity.PizzaSize;
import com.tom.thomasspencerpizzaproject.service.PizzaService;
import com.tom.thomasspencerpizzaproject.service.PizzaSizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzaSize")
public class PizzaSizeController {
    private final PizzaSizeService pizzaSizeService;

    public PizzaSizeController(PizzaSizeService psc) {
        this.pizzaSizeService = psc;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        try {
            List<PizzaSize> pizzaSizes = pizzaSizeService.getAllPizzaSizes();
            return ResponseEntity.ok(pizzaSizes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPizza(@PathVariable int id) {
        try {
            PizzaSize pizzaSize = pizzaSizeService.getPizzaSizeById(id);
            if (pizzaSize != null) {
                return ResponseEntity.ok(pizzaSize);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
