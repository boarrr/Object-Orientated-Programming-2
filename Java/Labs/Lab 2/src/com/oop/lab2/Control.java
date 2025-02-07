/*
 * Control: The main control class for Lab 2, contains the main method
 * Author: Ryan Pitman
 * Date: 07-02-2024
 */

package com.oop.lab2;

import javax.swing.JTextField;
import javax.swing.SwingConstants; 

class Control {
    public static void main(String[] args) {
        Pet pet1 = new Pet("Olly", "Dog", false);

        // Print the instance of pet1
        System.out.println(pet1); 
        
        // Attempt to access attributes, fails due to private attribute
        // System.out.println(pet1.petName);

        pet1.makeNoise();

        // Create a new text field
        JTextField text = new JTextField("This is sample text");

        // Set alignment to 3 (SwingConstants.RIGHT is 3)
        text.setHorizontalAlignment(SwingConstants.RIGHT);

        // Create two strings
        String string1 = "This is the first string";
        String string2 = "This is the first string1";
        boolean areEqual = false;

        // Compare the strings and store the boolean response
        areEqual = string1.equals(string2);

        // Output the equality of the strings
        System.out.println("Are the strings equal?: " + areEqual);
    }
}
