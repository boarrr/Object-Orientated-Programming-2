/*
 * ElectricCar: This class is a subclass of the Car class to represent an electric car
 * Author: Ryan Pitman
 * Date: 13-02-2025
 */

package com.lab.week3;

public class ElectricCar extends Car {
    // Attributes
    private int range;

    // Constructor
    public ElectricCar(String brand, int year, int doors, int range) {
        super(brand, year, doors);
        setRange(range);
    }

    // Getter
    public int getRange() {
        return range;
    }

    // Setter
    public void setRange(int range) {
        this.range = range;
    }

    // toString overload
    @Override
    public String toString() {
        return super.toString() + " It has a range of " + getRange() + " km!";
    }

    @Override
    public void honk() {
        System.out.println("Prrrrrrr!\n");
    }
}
