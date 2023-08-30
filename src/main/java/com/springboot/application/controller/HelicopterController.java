package com.springboot.application.controller;

import com.springboot.application.entity.Helicopter;
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
    @GetMapping("{tailNumber}") // singleton resource - full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<?> getOneHelicopter (@PathVariable("tailNumber")@NotNull @NotBlank String tailNumber) {
        try {
            helicopterService.getOneHelicopter(tailNumber);
        } catch (NullPointerException e) {
            // Returns string
            return new ResponseEntity<>("Glider with this tail number, " + tailNumber + ", does not exist.  Not found", HttpStatus.NOT_FOUND);
        }
        // Returns a glider object
        return new ResponseEntity<>(helicopterService.getOneHelicopter(tailNumber), HttpStatus.OK);
    }


    // For POST method
    @PostMapping
    public ResponseEntity<String> createHelicopter(@Valid @RequestBody Helicopter helicopter) {
        try {
            helicopterService.createHelicopter(helicopter);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Can't be created", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>("Helicopter has been created", HttpStatus.CREATED);
    }

    // for DELETE method
    @DeleteMapping("{tailNumber}") // full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<String> deleteHelicopter(@PathVariable("tailNumber")@NotNull @NotBlank String tailNumber) {
        try {
            helicopterService.deleteHelicopter(tailNumber);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Cannot delete.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Helicopter with this tail number, " + tailNumber + ", has been deleted.", HttpStatus.OK);
    }

    // for PUT method
    @PutMapping("{tailNumber}")
    public ResponseEntity<String> updateHelicopter(@PathVariable("tailNumber") @NotNull @NotBlank String tailNumber, @Valid @RequestBody Helicopter newHelicopterDetail) {
        try {
            helicopterService.updateHelicopter(tailNumber, newHelicopterDetail);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot modify.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Helicopter with this tail number, " + tailNumber + ", has been modified.", HttpStatus.OK);
    }

}