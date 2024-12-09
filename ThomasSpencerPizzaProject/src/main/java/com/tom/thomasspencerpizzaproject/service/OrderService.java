package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Customer;
import com.tom.thomasspencerpizzaproject.entity.Orders;
import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import com.tom.thomasspencerpizzaproject.repository.CustomerRepository;
import com.tom.thomasspencerpizzaproject.repository.OrdersRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public OrderService(OrdersRepository or) {
        this.or = or;
    }

    private final OrdersRepository or;

    public List<Orders> getAllOrders() {
        return or.findAll();
    }

    public Orders getOrderById(int id) {
        return or.findById(id).get();
    }


    public Orders saveOrder(Orders order) {
        try {
            Orders savedOrder = or.save(order);
            return savedOrder;
        }
        catch (Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }


    public boolean deleteOrderById(int id) {
        try {
            if(or.existsById(id)) {
                or.deleteById(id);
                return true;
            }
            return false;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        catch(Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }
}
