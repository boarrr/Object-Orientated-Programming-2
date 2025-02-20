/*
 * Pet: A class to contain information regarding a pet, contains the attributes:
 *      Name, Type of animal, training status
 * Author: Ryan Pitman
 * Date: 07-02-2025
 */

package com.oop.lab2;

class Pet {
    // Attributes
    private String petName;
    private String petType;
    private boolean trained;
    
    // Constructor
    Pet(String name, String type, boolean train) {
        setName(name);
        setType(type);
        setTrain(train);
    }
    
    // Getters
    public String getName() {
        return petName;
    }

    public String getType() {
        return petType;
    }

    public boolean getTrain() {
        return trained;
    }

    // Setters
    public void setName(String name) {
        petName = name;
    }

    public void setType(String type) {
        petType = type;
    }

    public void setTrain(Boolean train) {
        trained = train;
    }

    // toString overload
    public String toString() {
        return "The pet named " + getName() + " is a " + getType() + " and has a training status: " + getTrain() + ".";
    }

    public void makeNoise() {
        // Get the type of animal
        String type = getType();

        // Print the noise based off of the type of animal
        if (type == "Dog") {
            System.out.println("Woof");
        }
        else if (type == "Cat") {
            System.out.println("Meow");
        }
        else { // Default case, no animal exists for this sound
            System.out.println("The animal is making no sound");
        }
    }
}
