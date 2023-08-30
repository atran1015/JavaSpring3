package com.springboot.application.service;
import com.springboot.application.entity.Glider;
import com.springboot.application.repository.GliderRepository;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GliderService {

    @Autowired
    private GliderRepository gliderRepository;
    

    // GET all method
    public List<Glider> getGliders()
    {
        return (List<Glider>)gliderRepository.findAll();
    }

    // POST method
    public Glider createGlider(Glider glider)
    {
        return gliderRepository.save(glider);
    }

    // GET one method
    public Glider getOneGlider(String tailNumber) {
        if (!gliderRepository.existsById(tailNumber)) {
            throw new NullPointerException();
        }
        return gliderRepository.findById(tailNumber).get();
    }

    // DELETE method
    public void deleteGlider(String tailNumber) {
        if (gliderRepository.existsById(tailNumber)) {
            gliderRepository.deleteById(tailNumber);
        } else {
            throw new NullPointerException();
        }
        
    }
    
    // PUT method
    public Glider updateGlider(String tailNumber, Glider newGliderDetail) {
        Glider gliderDB = gliderRepository.findById(tailNumber).get();
  
        if (Objects.nonNull(newGliderDetail.getTailNumber())) {
            gliderDB.setTailNumber(newGliderDetail.getTailNumber());
        }
  
        if (Objects.nonNull(newGliderDetail.getNumberOfWheels())) {
            gliderDB.setNumberOfWheels(newGliderDetail.getNumberOfWheels());
        }
  
        if (Objects.nonNull(newGliderDetail.getLength())) {
            gliderDB.setLength(newGliderDetail.getLength());
        }

        if (Objects.nonNull(newGliderDetail.getTowPlaneName())) {
            gliderDB.setTowPlaneName(newGliderDetail.getTowPlaneName());
        }
  
        return gliderRepository.save(gliderDB);
    }
}
