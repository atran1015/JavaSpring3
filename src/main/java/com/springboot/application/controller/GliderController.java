package com.springboot.application.controller;

import com.springboot.application.entity.Glider;
import com.springboot.application.service.GliderService;

import java.util.List;
import java.util.ArrayList;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping(path="api/v1/gliders")
public class GliderController {

    private final GliderService gliderService;

    // Constructor
    @Autowired // Instantiates the glider service first, then inject it into the constructor
    public GliderController(GliderService gliderService) {
        this.gliderService = gliderService;
    }

    // For GET method - retrieve ALL
    @GetMapping
    public List<Glider> getGliders() {
        return gliderService.getGliders();
    }

    // For GET method - retrieve ONE
    @GetMapping("{tailNumber}") // singleton resource - full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<?> getOneGlider (@PathVariable("tailNumber")@NotNull @NotBlank String tailNumber) {
        try {
            gliderService.getOneGlider(tailNumber);
        } catch (NullPointerException e) {
            // Returns string
            return new ResponseEntity<>("Glider with this tail number, " + tailNumber + ", does not exist.  Not found", HttpStatus.NOT_FOUND);
        }
        // Returns a glider object
        return new ResponseEntity<>(gliderService.getOneGlider(tailNumber), HttpStatus.OK);
    }


    // For POST method
    @PostMapping
    public ResponseEntity<String> createGlider(@Valid @RequestBody Glider glider) {
        try {
            gliderService.createGlider(glider);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Can't be created", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>("Glider has been created", HttpStatus.CREATED);
    }

    // for DELETE method
    @DeleteMapping("{tailNumber}") // full access to url would be http://localhost:8080/api/v1/gliders/1abc
    public ResponseEntity<String> deleteGlider(@PathVariable("tailNumber")@NotNull @NotBlank String tailNumber) {
        try {
            gliderService.deleteGlider(tailNumber);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Cannot delete.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Glider with this tail number, " + tailNumber + ", has been deleted.", HttpStatus.OK);
    }

    // for PUT method
    @PutMapping("{tailNumber}")
    public ResponseEntity<String> updateGlider(@PathVariable("tailNumber") @NotNull @NotBlank String tailNumber, @Valid @RequestBody Glider newGliderDetails) {
        try {
            gliderService.updateGlider(tailNumber, newGliderDetails);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot modify.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Glider with this tail number, " + tailNumber + ", has been modified.", HttpStatus.OK);
    }

}