/*
 * Person.java: A class that represents a person with a firstname, lastname and date of birth.
 * Author: Ryan Pitman
 * Date: 27/03/2025
 */

package com.week9.lab;

public class Person {
    // Attributes
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    // Constructor
    public Person(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    // toString method
    @Override
    public String toString() {
        return firstName + " " + lastName + " / " + dateOfBirth;
    }
}
