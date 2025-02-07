package com.lab1.test;

/*
 * Car: A class that contains information relating to a car object
 * Author: Ryan Pitman
 * Date: 30-1-2024
 */

public class Car {
    private String ownerName;
    private String regNumber;
    private String carColor;
    private boolean automatic;
    private int maxSpeed;
    private int wheelCount;

    // Constructor with two arguments
    public Car(String ownerName, String regNumber) {
        this.ownerName = ownerName;
        this.regNumber = regNumber;
    }

    // Constructor with all arguments
    public Car(String ownerName, String regNumber, String carColor, boolean automatic, int maxSpeed, int wheelCount) {
        this.ownerName = ownerName;
        this.regNumber = regNumber;
        this.carColor = carColor;
        this.automatic = automatic;
        this.maxSpeed = maxSpeed;
        this.wheelCount = wheelCount;
    }

    // To string method overload
    public String toString() {
       return "The car with registration " + regNumber + " is owned by " + ownerName + ". It is " + carColor + ", has a max speed of " + maxSpeed + ", " + wheelCount + " wheels, and automatic gear: " + automatic; 
    }
}