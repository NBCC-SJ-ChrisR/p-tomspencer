package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.entity.PizzaSize;
import com.tom.thomasspencerpizzaproject.repository.PizzaRepository;
import com.tom.thomasspencerpizzaproject.repository.PizzaSizeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaSizeService {

    public PizzaSizeService(PizzaSizeRepository psr) {
        this.psr = psr;
    }

    private final PizzaSizeRepository psr;

    public List<PizzaSize> getAllPizzaSizes() {
        return psr.findAll();
    }

    public PizzaSize getPizzaSizeById(int id) {
        return psr.findById(id).get();
    }

}
