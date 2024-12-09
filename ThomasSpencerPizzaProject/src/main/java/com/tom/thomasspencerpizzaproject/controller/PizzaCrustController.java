package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.PizzaCrust;
import com.tom.thomasspencerpizzaproject.entity.PizzaSize;
import com.tom.thomasspencerpizzaproject.service.PizzaCrustService;
import com.tom.thomasspencerpizzaproject.service.PizzaSizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pizzaCrust")
public class PizzaCrustController {
    private final PizzaCrustService pizzaCrustService;

    public PizzaCrustController(PizzaCrustService pcs) {
        this.pizzaCrustService = pcs;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        try {
            List<PizzaCrust> pizzaCrusts = pizzaCrustService.getAllPizzaCrusts();
            return ResponseEntity.ok(pizzaCrusts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPizza(@PathVariable int id) {
        try {
            PizzaCrust pizzaCrust = pizzaCrustService.getPizzaCrustById(id);
            if (pizzaCrust != null) {
                return ResponseEntity.ok(pizzaCrust);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
