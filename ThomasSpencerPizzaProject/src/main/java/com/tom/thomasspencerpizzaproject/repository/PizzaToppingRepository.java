package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaToppingRepository extends JpaRepository<PizzaTopping,Integer> {
}
