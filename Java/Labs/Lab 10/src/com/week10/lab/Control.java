/*
 * Control.java: A class that controls the flow of the program
 * Author: Ryan Pitman
 * Date: 03/04/2025
 */

package com.week10.lab;

public class Control {
    public static void main(String[] args) {

        System.out.println("\n--- StringItemManager ---");

        // Create a new StringItemManager
        StringItemManager stringItemManager = new StringItemManager();

        // Add 5 items to the list
        for (int i = 0; i < 5; i++) {
            stringItemManager.addItem("Item " + i);
        }

        // Print the list of items
        System.out.println(stringItemManager.getAllItems());

        // Get the item at index 2
        System.out.println(stringItemManager.getItemAt(2));
        
        // Get the item at an invalid index, compile error as you cannot access an index that is out of bounds
        // System.out.println(stringItemManager.getItemAt(10));

        // Try to add an integer to the list, compile error as the method is expecting a String
        // stringItemManager.addItem(1);

        System.out.println("\n--- IntegerItemManager ---");
        // Create a new IntegerItemManager
        IntegerItemManager integerItemManager = new IntegerItemManager();

        // Add 5 integers to the list
        for (int i = 0; i < 5; i++) {
            integerItemManager.addItem(i);
        }

        // Print the list of items
        System.out.println(integerItemManager.getAllItems());

        System.out.println("\n--- ItemManagerString ---");

        // Create a new itemManager for strings
        ItemManager<String> stringItemManager2 = new ItemManager<>();

        // Add 5 strings to the list
        for (int i = 0; i < 5; i++) {
            stringItemManager2.addItem("Item " + i);
        }

        // Print the list of items
        System.out.println(stringItemManager2.getAllItems());

        System.out.println("\n--- ItemManagerInteger ---");

        // Create a new ItemManager for ints
        ItemManager<Integer> integerItemManager2 = new ItemManager<>();

        // Add 5 integers to the list
        for (int i = 0; i < 5; i++) {
            integerItemManager2.addItem(i);
        }

        // Print the list of items
        System.out.println(integerItemManager2.getAllItems());
    }
}
