package com.springboot.application.controller;

import com.springboot.application.model.Jet;
import com.springboot.application.service.JetService;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping(path="api/v1/jets")
public class JetController {

    private final JetService jetService;

    // Constructor
    @Autowired // Instantiates the glider service first, then inject it into the constructor
    public JetController(JetService jetService) {
        this.jetService = jetService;
    }

    // For GET method - retrieve ALL
    @GetMapping
    public List<Jet> getJets() {
        return jetService.getJets();
    }

    // For GET method - retrieve ONE
    @GetMapping("{id}") // singleton resource - full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<?> getOneJet (@PathVariable("id")@NotNull Long id) {
        try {
            jetService.getOneJet(id);
        } catch (NullPointerException e) {
            // Returns string
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        // Returns a glider object
        return new ResponseEntity<>(jetService.getOneJet(id), HttpStatus.OK);
    }


    // For POST method
    @PostMapping
    public ResponseEntity<?> createJet(@Valid @RequestBody Jet jet) {
        try {
            jetService.createJet(jet);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Can't be created", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(jetService.createJet(jet), HttpStatus.CREATED);
    }

    // for DELETE method
    @DeleteMapping("{id}") // full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<String> deleteJet(@PathVariable("id")@NotNull Long id) {
        try {
            jetService.deleteJet(id);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Cannot delete.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }

    // for PUT method
    @PutMapping("{id}")
    public ResponseEntity<?> updateJet(@PathVariable("id") @NotNull Long id, @Valid @RequestBody Jet newJetDetail) {
        try {
            jetService.updateJet(id, newJetDetail);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot modify.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(jetService.updateJet(id, newJetDetail), HttpStatus.OK);
    }

}