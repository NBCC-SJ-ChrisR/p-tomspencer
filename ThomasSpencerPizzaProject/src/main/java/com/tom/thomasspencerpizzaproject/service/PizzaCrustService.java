package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.PizzaCrust;
import com.tom.thomasspencerpizzaproject.entity.PizzaSize;
import com.tom.thomasspencerpizzaproject.repository.PizzaCrustRepository;
import com.tom.thomasspencerpizzaproject.repository.PizzaSizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaCrustService {

    public PizzaCrustService(PizzaCrustRepository pcr) {
        this.pcr = pcr;
    }

    private final PizzaCrustRepository pcr;

    public List<PizzaCrust> getAllPizzaCrusts() {
        return pcr.findAll();
    }

    public PizzaCrust getPizzaCrustById(int id) {
        return pcr.findById(id).get();
    }

}
