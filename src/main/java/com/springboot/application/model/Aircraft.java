package com.springboot.application.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "aircraft")
@Inheritance( strategy = InheritanceType.JOINED ) // For joined table operation
public abstract class Aircraft implements Serializable{ // Converts Java objects into byte streams for it to be stored in persistent memory
    
    @Id //Primary key of current identity
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO) // Generating id
    protected Long id;

    @Column(name = "numberOfWheels", nullable = false)
    protected int numberOfWheels;

    @Column(name = "length", nullable = false)
    protected int length;

    // Default Constructor
    public Aircraft() {}

    // Default Constructor
    public Aircraft(Long id, int wheelsNum, int length) {
        this.id = id;
        this.numberOfWheels = wheelsNum;
        this.length = length;
    }

    // Setters and Getters
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setNumberOfWheels(int wheelsNum) {
        this.numberOfWheels = wheelsNum;
    }
    public int getNumberOfWheels() {
        return numberOfWheels;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public int getLength() {
        return length;
    }

    // Behaviors/Actions - these methods can be used or overridden by subclasses
    public void takeOff() {
        System.out.println("The Aircraft is taking off.");
    }
    public void fly() {
        System.out.println("The Aircraft is flying.");
    }
    public void land() {
        System.out.println("The Aircraft is landing.");
    }
    public void printDetails() {
        System.out.println("Id: " + getId());
        System.out.println("Number of Wheels: " + getNumberOfWheels());
        System.out.println("Length: " + getLength());
    }
}