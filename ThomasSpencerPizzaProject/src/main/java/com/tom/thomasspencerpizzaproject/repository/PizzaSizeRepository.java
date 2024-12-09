package com.tom.thomasspencerpizzaproject.repository;

import com.tom.thomasspencerpizzaproject.entity.PizzaSize;
import com.tom.thomasspencerpizzaproject.entity.PizzaTopping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaSizeRepository extends JpaRepository<PizzaSize,Integer> {
}
