/*
 * IntegerItemManager.java: A class that manages a list of integers
 * Contains three methods:
 * - addItem: Adds an integer to the list
 * - getItemAt: Returns an integer from the list at a given index
 * - getAllItems: Returns the list of integers
 * Author: Ryan Pitman
 * Date: 03/04/2025
 */

package com.week10.lab;

import java.util.ArrayList;

public class IntegerItemManager {
    private ArrayList<Integer> items;

    public IntegerItemManager() {
        items = new ArrayList<>();
    }

    public void addItem(Integer item) {
        items.add(item);
    }

    public Integer getItemAt(int index) {
        return items.get(index);
    }

    public ArrayList<Integer> getAllItems() {
        return items;
    }
}