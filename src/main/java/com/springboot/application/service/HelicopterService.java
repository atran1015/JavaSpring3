package com.springboot.application.service;
import com.springboot.application.entity.Helicopter;
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
    public Helicopter getOneHelicopter(String tailNumber) {
        if (!helicopterRepository.existsById(tailNumber)) {
            throw new NullPointerException();
        }
        return helicopterRepository.findById(tailNumber).get();
    }

    // DELETE method
    public void deleteHelicopter(String tailNumber) {
        if (helicopterRepository.existsById(tailNumber)) {
            helicopterRepository.deleteById(tailNumber);
        } else {
            throw new NullPointerException();
        }
        
    }
    
    // PUT method
    public Helicopter updateHelicopter(String tailNumber, Helicopter newHelicopterDetail) {
        Helicopter helicopterDB = helicopterRepository.findById(tailNumber).get();
  
        if (Objects.nonNull(newHelicopterDetail.getTailNumber())) {
            helicopterDB.setTailNumber(newHelicopterDetail.getTailNumber());
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
