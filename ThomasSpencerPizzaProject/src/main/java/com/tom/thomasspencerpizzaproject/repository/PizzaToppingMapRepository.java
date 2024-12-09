package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import com.tom.thomasspencerpizzaproject.entity.PizzaToppingMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaToppingMapRepository extends JpaRepository<PizzaToppingMap,Integer> {
}
