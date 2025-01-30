package com.lab1.test;

/*
 * Control: A class that controls the main flow of the program and allows interaction
 * Author: Ryan Pitman
 * Date: 30-1-2024
 */

public class Control {
    public static void main(String args[]) {
        System.out.println("First lab");

        Car car1 = new Car("Ryan", "10MH15358");
        Car car2 = new Car("Ryan", "10MH15358", "Silver", false, 100, 4);

        System.out.println(car2);
    }
}
