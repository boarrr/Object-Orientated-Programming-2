/*
 * LabHashSet.java: A class that implents a hash set utilising Java Collections
 * Author: Ryan Pitman
 * Date: 27/03/2025
 */

package com.week9.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

class LabHashSet {
    // Attributes
    private HashSet<String> hashSet;

    // Constructor
    public LabHashSet() {
        String[] counties = {
            "Antrim", "Armagh", "Carlow", "Cavan", "Clare", "Cork", "Derry", "Donegal", "Down", "Dublin",
            "Fermanagh", "Galway", "Kerry", "Kildare", "Kilkenny", "Laois", "Leitrim", "Limerick", "Longford",
            "Louth", "Mayo", "Meath", "Monaghan", "Offaly", "Roscommon", "Sligo", "Tipperary", "Tyrone",
            "Waterford", "Westmeath", "Wexford", "Wicklow" 
        };

        hashSet = new HashSet<>();

        for (String county : counties) {
            hashSet.add(county);
        }
    }

    // Method to print the hash set
    public void printSet() {
        System.out.println("\nCounties in the hash set: ");

        Iterator<String> iterator = hashSet.iterator();

        while (iterator.hasNext()) {
            String county = iterator.next();
            System.out.println(county);
        }
    }

    // Method to add a county to the hash set
    public void addEntry(String county) {
        hashSet.add(county);
    }

    // Method to check if a value exists in the hash set
    public boolean contains(String county) {
        return hashSet.contains(county);
    }

    // Method to clear the hash set
    public void clearSet() {
        hashSet.clear();
    }

    // Method to sort the hash set
    public void sortSet() {
        // Convert the hash set to a list
        List<String> sortedSet = new ArrayList<>(hashSet);

        // Sort the list
        Collections.sort(sortedSet);

        // Print the sorted list
        System.out.println("\nCounties in the sorted list: ");

        for (String county : sortedSet) {
            System.out.println(county);
        }
    }

    // Method to reverse the hash set
    public void reverseSet() {
        // Convert the hash set to a list
        List<String> reversedSet = new ArrayList<>(hashSet);

        // Reverse the list
        Collections.sort(reversedSet, Collections.reverseOrder());
    
        // Print the reversed list
        System.out.println("\nCounties in the reversed list: ");

        for (String county : reversedSet) {
            System.out.println(county);
        }
    }

    // Method to shuffle the hash set
    public void shuffleSet() {
        // Convert the hash set to a list
        List<String> shuffledSet = new ArrayList<>(hashSet);

        // Shuffle the list
        Collections.shuffle(shuffledSet);

        // Print the shuffled list
        System.out.println("\nCounties in the shuffled list: ");

        for (String county : shuffledSet) {
            System.out.println(county);
        }
    } 
}