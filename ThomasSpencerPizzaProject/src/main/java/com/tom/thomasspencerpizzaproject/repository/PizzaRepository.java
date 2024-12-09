package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.Orders;
import com.tom.thomasspencerpizzaproject.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Integer> {
}
