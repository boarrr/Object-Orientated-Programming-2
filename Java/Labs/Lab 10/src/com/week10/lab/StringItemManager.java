/*
 * StringItemManager.java: A class that manages a list of strings
 * Contains three methods:
 * - addItem: Adds a string to the list
 * - getItemAt: Returns a string from the list at a given index
 * - getAllItems: Returns the list of strings
 * Author: Ryan Pitman
 * Date: 03/04/2025
 */

package com.week10.lab;

import java.util.ArrayList;

public class StringItemManager {
    private ArrayList<String> items;

    public StringItemManager() {
        items = new ArrayList<>();
    }

    public void addItem(String item) {
        items.add(item);
    }

    public String getItemAt(int index) {
        return items.get(index);
    }

    public ArrayList<String> getAllItems() {
        return items;
    }
}
