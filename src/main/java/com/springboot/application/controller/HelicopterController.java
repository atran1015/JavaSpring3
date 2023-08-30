package com.springboot.application.controller;

import com.springboot.application.model.Helicopter;
import com.springboot.application.service.HelicopterService;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping(path="api/v1/helicopters")
public class HelicopterController {

    private final HelicopterService helicopterService;

    // Constructor
    @Autowired // Instantiates the glider service first, then inject it into the constructor
    public HelicopterController(HelicopterService helicopterService) {
        this.helicopterService = helicopterService;
    }

    // For GET method - retrieve ALL
    @GetMapping
    public List<Helicopter> getHelicopters() {
        return helicopterService.getHelicopters();
    }

    // For GET method - retrieve ONE
    @GetMapping("{id}") // singleton resource - full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<?> getOneHelicopter (@PathVariable("id")@NotNull Long id) {
        try {
            helicopterService.getOneHelicopter(id);
        } catch (NullPointerException e) {
            // Returns string
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        // Returns a glider object
        return new ResponseEntity<>(helicopterService.getOneHelicopter(id), HttpStatus.OK);
    }


    // For POST method
    @PostMapping
    public ResponseEntity<?> createHelicopter(@Valid @RequestBody Helicopter helicopter) {
        try {
            helicopterService.createHelicopter(helicopter);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Can't be created", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(helicopterService.createHelicopter(helicopter), HttpStatus.CREATED);
    }

    // for DELETE method
    @DeleteMapping("{id}") // full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<String> deleteHelicopter(@PathVariable("id")@NotNull Long id) {
        try {
            helicopterService.deleteHelicopter(id);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Cannot delete.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }

    // for PUT method
    @PutMapping("{id}")
    public ResponseEntity<?> updateHelicopter(@PathVariable("id") @NotNull Long id, @Valid @RequestBody Helicopter newHelicopterDetail) {
        try {
            helicopterService.updateHelicopter(id, newHelicopterDetail);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot modify.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(helicopterService.updateHelicopter(id, newHelicopterDetail), HttpStatus.OK);
    }

}