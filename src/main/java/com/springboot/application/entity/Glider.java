package com.springboot.application.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "glider")
@PrimaryKeyJoinColumn(referencedColumnName="tailNumber")
public class Glider extends Aircraft {
    // Unique to Glider - no engine, uses tow plane
    @NotNull(message = "Tow Plane Name cannot be null")
    @NotBlank(message = "Tow Plane Name cannot be null")
    private String towPlane;
    // Default Constructor
    public Glider() {}

    // Default Constructor
    public Glider(String tailNum, int wheelsNum, int length, String towPlaneName) {
        super(tailNum, wheelsNum, length);
        this.towPlane = towPlaneName;
    }

    // Setter and Getter
    public void setTowPlaneName(String towPlaneName) {
		this.towPlane = towPlaneName;
	}
	public String getTowPlaneName() {
		return towPlane;
	}

    // Method override
    @Override
    public void printDetails() {
        System.out.println("Tail Number: " + getTailNumber());
        System.out.println("Number of Wheels: " + getNumberOfWheels());
        System.out.println("Length: " + getLength());
        System.out.println("Name of Towplane: " + getTowPlaneName());
    }
}