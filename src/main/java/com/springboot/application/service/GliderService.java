package com.springboot.application.service;
import com.springboot.application.model.Glider;
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
    public Glider getOneGlider(Long id) {
        // Throw error if glider doesn't exist
        if (!gliderRepository.existsById(id)) {
            throw new NullPointerException();
        }
        return gliderRepository.findById(id).get();
    }

    // DELETE method
    public void deleteGlider(Long id) {
        if (gliderRepository.existsById(id)) {
            gliderRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
        
    }
    
    // PUT method
    public Glider updateGlider(Long id, Glider newGliderDetail) {
        Glider gliderDB = gliderRepository.findById(id).get();

        // Checks whether the input object reference supplied to it is non-null or not
        if (Objects.nonNull(newGliderDetail.getId())) {
            gliderDB.setId(newGliderDetail.getId());
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
