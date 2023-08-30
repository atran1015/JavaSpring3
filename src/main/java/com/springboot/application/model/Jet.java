package com.springboot.application.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "jet")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Jet extends Aircraft {
    @NotNull(message = "Fuel Name cannot be null")
    @NotBlank(message = "Fuel Name cannot be null")
    private String fuel;

    // Default Constructor
    public Jet() {}

    // Default Constructor
    public Jet(Long id, int wheelsNum, int length, String fuel) {
        super(id, wheelsNum, length);
        this.fuel = fuel;
    }

    // Setters and Getters
    public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getFuel() {
		return fuel;
	}

    // Method override
    @Override
    public void printDetails() {
        System.out.println("Id: " + getId());
        System.out.println("Number of Wheels: " + getNumberOfWheels());
        System.out.println("Length: " + getLength());
        System.out.println("Name of Fuel: " + getFuel());
    }
}