/*
 * Control.java: A class that controls the flow of the program
 * Author: Ryan Pitman
 * Date: 27/03/2025
 */

package com.week9.lab;

import java.util.HashSet;

public class Control {
    public static void main(String[] args) {
        // Create a new LabHashSet object
        LabHashSet hashSet = new LabHashSet();
        
        // Print the hash set
        hashSet.printSet();

        System.out.println("\nAdding a new county to the hash set...");

        // Add a new county to the hash set
        hashSet.addEntry("Hibernia");

        System.out.println("\nPrinting the hash set again...");

        // Print the hash set again
        hashSet.printSet();

        // Match entry checks
        System.out.println("\nChecking if the hash set contains 'London': "  + hashSet.contains("London"));
        System.out.println("\nChecking if the hash set contains 'Louth': " + hashSet.contains("Louth"));

        // Test sorting the hash set
        System.out.println("\nSorting the hash set...");
        hashSet.sortSet();

        // Test reversing the hash set
        System.out.println("\nReversing the hash set...");
        hashSet.reverseSet();

        // Test shuffling the hash set
        System.out.println("\nShuffling the hash set...");
        hashSet.shuffleSet();

        // Clear the hash set
        System.out.println("\nClearing the hash set...");
        hashSet.clearSet();

        // Create a persons hash set
        HashSet<Person> personSet = new HashSet<>();

        // Instantiate 5 Person objects, 2 with the same information
        Person person1 = new Person("John", "Doe", "1990-01-01");
        Person person2 = new Person("John", "Doe", "1990-01-01");
        Person person3 = new Person("Jim", "Beam", "1985-05-05");
        Person person4 = new Person("Jill", "Bush", "1980-10-10");
        Person person5 = new Person("Jack", "Daniels", "1975-12-15");

        // Add the Person objects to the hash set
        personSet.add(person1);
        personSet.add(person2);
        personSet.add(person3);
        personSet.add(person4);
        personSet.add(person5);

        // Print the persons hash set
        System.out.println("\nPrinting the persons hash set...");
        for (Person person : personSet) {
            System.out.println(person);
        }

        // Test sorting the persons hash set using collections sort, will fail as Persons is not a collection (list)
        // System.out.println("\nSorting the persons hash set...");
        // Collections.sort(personSet);
    }
}
