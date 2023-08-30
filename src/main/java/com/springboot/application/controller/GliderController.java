package com.springboot.application.controller;

import com.springboot.application.model.Glider;
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
    @GetMapping("{id}")
    public ResponseEntity<?> getOneGlider (@PathVariable("id")@NotNull Long id) {
        try {
            gliderService.getOneGlider(id);
        } catch (NullPointerException e) {
            // Returns string
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }
        // Returns a glider object
        return new ResponseEntity<>(gliderService.getOneGlider(id), HttpStatus.OK);
    }


    // For POST method
    @PostMapping
    public ResponseEntity<?> createGlider(@Valid @RequestBody Glider glider) {
        try {
            gliderService.createGlider(glider);
        // TODO: try to catch internal server error as well
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Can't be created", HttpStatus.BAD_REQUEST);
        }
        
        
        return new ResponseEntity<>(gliderService.createGlider(glider), HttpStatus.CREATED);
    }

    // for DELETE method
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGlider(@PathVariable("id")@NotNull Long id) {
        try {
            gliderService.deleteGlider(id);
        } catch (NullPointerException e) {
            return new ResponseEntity<>("Cannot delete.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }

    // for PUT method
    @PutMapping("{id}")
    public ResponseEntity<?> updateGlider(@PathVariable("id") @NotNull Long id, @Valid @RequestBody Glider newGliderDetails) {
        try {
            gliderService.updateGlider(id, newGliderDetails);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot modify.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(gliderService.updateGlider(id, newGliderDetails), HttpStatus.OK);
    }

}