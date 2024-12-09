package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Employee;
import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import com.tom.thomasspencerpizzaproject.repository.EmployeeRepository;
import com.tom.thomasspencerpizzaproject.repository.PizzaToppingRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaToppingService {

    public PizzaToppingService(PizzaToppingRepository ptr, ApplicationEventPublisher eventPublisher) {
        this.ptr = ptr;
        this.eventPublisher = eventPublisher;
    }

    private final PizzaToppingRepository ptr;
    private final ApplicationEventPublisher eventPublisher;

    public List<PizzaTopping> getAllPizzaToppings() {
        return ptr.findAll();
    }

    public PizzaTopping getPizzaToppingById(int id) {
        return ptr.findById(id).get();
    }

    public PizzaTopping savePizzaTopping(PizzaTopping pizzaTopping) {
        try {
            PizzaTopping savedPizzaTopping = ptr.save(pizzaTopping);
            return savedPizzaTopping;
        }
        catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public void deletePizzaToppingById(int id) {
        try {
            ptr.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        catch(Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}
