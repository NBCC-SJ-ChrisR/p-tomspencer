package com.tom.thomasspencerpizzaproject.service;

import com.tom.thomasspencerpizzaproject.entity.Pizza;
import com.tom.thomasspencerpizzaproject.entity.PizzaToppingMap;
import com.tom.thomasspencerpizzaproject.repository.PizzaRepository;
import com.tom.thomasspencerpizzaproject.repository.PizzaToppingMapRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PizzaToppingMapService {

    public PizzaToppingMapService(PizzaToppingMapRepository ptmr) {
        this.ptmr = ptmr;
    }

    private final PizzaToppingMapRepository ptmr;

    public List<PizzaToppingMap> getAllPizzaToppingMaps() {
        return ptmr.findAll();
    }

    public PizzaToppingMap getPizzaToppingMapById(int id) {
        return ptmr.findById(id).get();
    }

    public PizzaToppingMap savePizzaToppingMap(PizzaToppingMap pizzaToppingMap) {
        try {
            PizzaToppingMap savedPizzaToppingMap = ptmr.save(pizzaToppingMap);
            return savedPizzaToppingMap;
        }
        catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public boolean deletePizzaToppingMapById(int id) {
        try {
            if(ptmr.existsById(id)) {
                ptmr.deleteById(id);
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
