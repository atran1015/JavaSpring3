package com.springboot.application.service;
import com.springboot.application.model.Jet;
import com.springboot.application.repository.JetRepository;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class JetService {

    @Autowired
    private JetRepository jetRepository;
    

    // GET all method
    public List<Jet> getJets()
    {
        return (List<Jet>)jetRepository.findAll();
    }

    // POST method
    public Jet createJet(Jet jet)
    {
        return jetRepository.save(jet);
    }

    // GET one method
    public Jet getOneJet(Long id) {
        if (!jetRepository.existsById(id)) {
            throw new NullPointerException();
        }
        return jetRepository.findById(id).get();
    }

    // DELETE method
    public void deleteJet(Long id) {
        if (jetRepository.existsById(id)) {
            jetRepository.deleteById(id);
        } else {
            throw new NullPointerException();
        }
        
    }
    
    // PUT method
    public Jet updateJet(Long id, Jet newJetDetail) {
        Jet jetDB = jetRepository.findById(id).get();
  
        if (Objects.nonNull(newJetDetail.getId())) {
            jetDB.setId(newJetDetail.getId());
        }
  
        if (Objects.nonNull(newJetDetail.getNumberOfWheels())) {
            jetDB.setNumberOfWheels(newJetDetail.getNumberOfWheels());
        }
  
        if (Objects.nonNull(newJetDetail.getLength())) {
            jetDB.setLength(newJetDetail.getLength());
        }

        if (Objects.nonNull(newJetDetail.getFuel())) {
            jetDB.setFuel(newJetDetail.getFuel());
        }
  
        return jetRepository.save(jetDB);
    }
}
