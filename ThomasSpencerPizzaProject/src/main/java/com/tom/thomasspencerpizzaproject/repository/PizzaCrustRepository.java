package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.entity.PizzaCrust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaCrustRepository extends JpaRepository<PizzaCrust,Integer> {
}
