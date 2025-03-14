/*
 * Control: A class that acts as the main class for the program. Also contains an arraylist of person objects
 * Author: Ryan Pitman
 * Date: 13-03-2024
 */

package com.week7.lab;

import java.util.ArrayList;

public class Control {
    public static void main(String[] args) {
        // Create an ArrayList of person objects
        ArrayList<Person> persons = new ArrayList<Person>();

        // Add 6 person objects to the arraylist
        persons.add(new Person("Jane", "Doe", "Belfast"));
        persons.add(new Person("John", "Smith", "Dublin"));
        persons.add(new Person("Alice", "Jones", "London"));
        persons.add(new Person("Bob", "Brown", "Paris"));
        persons.add(new Person("Eve", "White", "Berlin"));
        persons.add(new Person("Charlie", "Black", "Madrid"));

        // Print the length of the arraylist
        System.out.println("The length of the arraylist is: " + persons.size());

        System.out.println("\nThe persons in the arraylist are:");

        // Loop through the array list and print each person
        for (Person person : persons) {
            System.out.println(person);
        }

        // Copy the arraylist to a new arraylist
        ArrayList<Person> personsCopy = new ArrayList<Person>(persons);

        System.out.println("\nThe persons in the copy arraylist are:");

        // Print the copy arraylist
        for (Person person : personsCopy) {
            System.out.println(person);
        }

        // Display the person GUI
        PersonGUI personGUI = new PersonGUI();
    }    
}
