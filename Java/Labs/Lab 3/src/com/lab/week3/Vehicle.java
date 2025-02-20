/*
 * Vehicle: A super class for the Motorcycle and Car classes, which contains a default implementation of the accelerate and brake methods
 * Author: Ryan Pitman
 * Date: 13-02-2025
 */

package com.lab.week3;

public class Vehicle {
    // Shared attributes
    private int speed = 0;
    private String brand;
    private int year;

    // Default constructor
    public Vehicle(String brand, int year) {
        setBrand(brand);
        setYear(year);
    }

    // Getters
    public int getSpeed() {
        return speed;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // toString overload
    @Override
    public String toString() {
        return "The " + getYear() + " " + getBrand() + " has a current speed of " + getSpeed() + "!";
    }

    // Accelerate method
    public void accelerate(int speed) {
        setSpeed(getSpeed() + speed);
        System.out.println("The " + getBrand() + " is now going " + getSpeed() + " km/h!");
    }

    // Brake method
    public void brake(int speed) {
        setSpeed(getSpeed() - speed);
        System.out.println("The " + getBrand() + " is now going " + getSpeed() + " km/h!\n");
    }

    // Default honk method
    public void honk() {
        System.out.println("Honk! Honk!\n");
    }
}
