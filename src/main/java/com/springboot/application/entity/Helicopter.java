package com.springboot.application.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "helicopter")
@PrimaryKeyJoinColumn(referencedColumnName="tailNumber")
public class Helicopter extends Aircraft {
    @NotNull(message = "Rotor speed cannot be null")
    private int rotorRpm;

    // Default Constructor
    public Helicopter() {}

    // Default Constructor
    public Helicopter(String tailNum, int wheelsNum, int length, int rpm) {
        super(tailNum, wheelsNum, length);
        this.rotorRpm = rpm;
    }

    // Setters and Getters
    public void setRotorRpm(int rpm) {
		this.rotorRpm = rpm;
	}
	public int getRotorRpm() {
		return rotorRpm;
	}

    // Method override
    @Override
    public void printDetails() {
        System.out.println("Tail Number: " + getTailNumber());
        System.out.println("Number of Wheels: " + getNumberOfWheels());
        System.out.println("Length: " + getLength());
        System.out.println("Rotor RPM: " + getRotorRpm());
    }
}