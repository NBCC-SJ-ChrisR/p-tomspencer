package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.Customer;
import com.tom.thomasspencerpizzaproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<Employee> findByUsername(String username);
}
