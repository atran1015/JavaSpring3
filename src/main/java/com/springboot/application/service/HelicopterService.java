package com.springboot.application.service;
import com.springboot.application.model.Helicopter;
import com.springboot.application.repository.HelicopterRepository;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HelicopterService {

    @Autowired
    private HelicopterRepository helicopterRepository;
    

    // GET all method
    public List<Helicopter> getHelicopters()
    {
        return (List<Helicopter>)helicopterRepository.findAll();
    }

    // POST method
    public Helicopter createHelicopter(Helicopter helicopter)
    {
        return helicopterRepository.save(helicopter);
    }

    // GET one method
    public Helicopter getOneHelicopter(Long id) {
        if (!helicopterRepository.existsById(id)) {
            throw new NullPointerException();
        }
        return helicopterRepository.findById(id).get();
    }

    // DELETE method
    public void deleteHelicopter(Long id) {
        if (helicopterRepository.existsById(id)) {
            helicopterRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
        
    }
    
    // PUT method
    public Helicopter updateHelicopter(Long id, Helicopter newHelicopterDetail) {
        Helicopter helicopterDB = helicopterRepository.findById(id).get();
  
        if (Objects.nonNull(newHelicopterDetail.getId())) {
            helicopterDB.setId(newHelicopterDetail.getId());
        }
  
        if (Objects.nonNull(newHelicopterDetail.getNumberOfWheels())) {
            helicopterDB.setNumberOfWheels(newHelicopterDetail.getNumberOfWheels());
        }
  
        if (Objects.nonNull(newHelicopterDetail.getLength())) {
            helicopterDB.setLength(newHelicopterDetail.getLength());
        }

        if (Objects.nonNull(newHelicopterDetail.getRotorRpm())) {
            helicopterDB.setRotorRpm(newHelicopterDetail.getRotorRpm());
        }
  
        return helicopterRepository.save(helicopterDB);
    }
}
