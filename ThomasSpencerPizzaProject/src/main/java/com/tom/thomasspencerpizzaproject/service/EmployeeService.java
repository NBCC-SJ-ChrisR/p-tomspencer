package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Customer;
import com.tom.thomasspencerpizzaproject.entity.Employee;
import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.repository.CustomerRepository;
import com.tom.thomasspencerpizzaproject.repository.EmployeeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public EmployeeService(EmployeeRepository er) {
        this.er = er;
    }

    private final EmployeeRepository er;

    public List<Employee> getAllEmployees() {
        return er.findAll();
    }

    public Employee getEmployeeById(int id) {
        return er.findById(id).get();
    }

    public void deleteEmployeeById(int id) {
        try {
            er.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
        catch(Exception e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public Employee findByUsername(String username) {
        return er.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Employee with username " + username + " not found."));
    }
}
