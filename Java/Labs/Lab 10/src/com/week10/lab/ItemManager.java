
/*
 * ItemManager.java: A class that manages a list of items of any type
 * Contains three methods:
 * - addItem: Adds an item to the list
 * - getItemAt: Returns an item from the list at a given index
 * - getAllItems: Returns the list of items
 * Author: Ryan Pitman
 * Date: 03/04/2025
 */

package com.week10.lab;

import java.util.ArrayList;

public class ItemManager<T> {
    private ArrayList<T> items;

    public ItemManager() {
        items = new ArrayList<>();
    }
    
    public void addItem(T item) {
        items.add(item);
    }

    public T getItemAt(int index) {
        return items.get(index);
    }

    public ArrayList<T> getAllItems() {
        return items;
    }
    
}
