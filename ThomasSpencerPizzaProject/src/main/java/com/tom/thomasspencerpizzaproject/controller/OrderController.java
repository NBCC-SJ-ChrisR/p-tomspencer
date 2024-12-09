package com.tom.thomasspencerpizzaproject.controller;

import com.tom.thomasspencerpizzaproject.entity.Employee;
import com.tom.thomasspencerpizzaproject.entity.Orders;
import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import com.tom.thomasspencerpizzaproject.service.EmployeeService;
import com.tom.thomasspencerpizzaproject.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService os) {
        this.orderService = os;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        try {
            List<Orders> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        try {
            Orders order = orderService.getOrderById(id);
            if (order != null) {
                return ResponseEntity.ok(order);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody Orders order) {
        try {
            System.out.println("Put req for order: " + order);
            order.setOrderId(id); // Ensure the ID is set
            Orders updatedOrder = orderService.saveOrder(order);
            return ResponseEntity.ok(updatedOrder); // Return 200 OK with updated employee
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Return 409 Conflict with error message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while updating the pizza topping.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Orders order) {
        try {
            System.out.println("Post req for order: " + order);
            Orders savedOrder = orderService.saveOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder); // Return 201 Created
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // Return 409 Conflict with error message
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred while creating the order.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        try {
            boolean isDeleted = orderService.deleteOrderById(id);
            if (isDeleted) {
                return ResponseEntity.noContent().build(); // Return 204 No Content
            } else {
                return ResponseEntity.notFound().build(); // Return 404 Not Found if order doesn't exist
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred while deleting the pizza.\n" + e.getMessage());
        }
    }
}
