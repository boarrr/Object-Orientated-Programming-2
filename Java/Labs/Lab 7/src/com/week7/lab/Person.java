/*
 * Person: A class that represents a person with a firstname, surname and city
 * Author: Ryan Pitman
 * Date: 13-03-2024
 */

package com.week7.lab;

public class Person {
    // Attributes
    private String firstname;
    private String surname;
    private String city;

    // Constructor
    public Person(String firstname, String surname, String city) {
        this.firstname = firstname;
        this.surname = surname;
        this.city = city;
    }

    // toString override
    @Override
    public String toString() {
        return "This person is called " + firstname + " " + surname + " and lives in " + city;
    }   

    // Getters
    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getCity() {
        return city;
    }

    // Setters
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
