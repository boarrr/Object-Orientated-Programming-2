/*
 * Car: A class to hold information regarding a car, contains the attributes:
 *     Brand, Year, Speed, Number of doors
 * Author: Ryan Pitman
 * Date: 13-02-2025
 */

package com.lab.week3;

public class Car extends Vehicle {
    // Attributes
    private int doors;

    // Default constructor
    public Car(String brand, int year, int doors) {
        super(brand, year);
        setDoors(doors);
    }

    // Getter
    public int getDoors() {
        return doors;
    }

    // Setter
    public void setDoors(int doors) {
        this.doors = doors;
    }

    // toString overload
    @Override
    public String toString() {
        return super.toString() + " It has " + getDoors() + " doors!";
    }

    @Override
    public void honk() {
        System.out.println("Beep beep!\n");
    }
}
