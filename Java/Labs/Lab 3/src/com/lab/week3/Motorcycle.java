/*
 * Motorcycle: A class to hold information regarding a motorcycle, contains the attributes:
 *    Brand, Year, Speed, Two Seater
 * Author: Ryan Pitman
 * Date: 13-02-2025
 */

package com.lab.week3;

public class Motorcycle extends Vehicle {
    // Attributes
    private boolean twoSeater;
    
    // Default constructor
    public Motorcycle(String brand, int year, boolean twoSeater) {
        super(brand, year);
        setTwoSeater(twoSeater);
    }

    // Getters
    public boolean getTwoSeater() {
        return twoSeater;
    }

    // Setters
    public void setTwoSeater(boolean twoSeater) {
        this.twoSeater = twoSeater;
    }

    // toString overload
    @Override
    public String toString() {
        return super.toString() + " It is a " + (getTwoSeater() ? "two seater" : "single seater") + "!";
    }

    @Override
    public void honk() {
        System.out.println("Vroooom!\n");
    }
}
