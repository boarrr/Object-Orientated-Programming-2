/*
 * Control: The main controller class of the program. This class will be used to instantiate objects and test their methods
 * Author: Ryan Pitman
 * Date: 13-02-2025
 */

package com.lab.week3;

public class Control {
    public static void main(String[] args) {
        // Create a new car object
        Car car1 = new Car("Ford", 2020, 4);   
        
        // Create a new motorcycle object
        Motorcycle motorcycle1 = new Motorcycle("Harley Davidson", 2020, false);

        // Create a new electric car object
        ElectricCar electricCar1 = new ElectricCar("Tesla", 2020, 4, 500);

        // Test the methods of the car object
        car1.accelerate(50);
        car1.brake(20);

        // Test the methods of the motorcycle object
        motorcycle1.accelerate(100);
        motorcycle1.brake(50);

        System.out.println("");

        // Test the methods of the electric car object
        electricCar1.accelerate(100);
        electricCar1.brake(50);

        // Test the honk method of the car object
        car1.honk();
        motorcycle1.honk();
        electricCar1.honk();
    }
}
