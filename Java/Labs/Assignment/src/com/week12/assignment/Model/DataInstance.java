/*
 * DataInstance.java is a class that represents the data instances of the dataset.
 * It stores the features and the label of each data instance as it is loaded
 * It provides getters to pull the features and label when needed
 * 
 * Author: Ryan Pitman
 * Date: 20-04-2025
 */

package com.week12.assignment.Model;

public class DataInstance {
    private String feature1;
    private String feature2;
    private String feature3;
    private String feature4;
    private String label;

    public DataInstance(String feature1, String feature2, String feature3, String feature4, String label) {
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.feature3 = feature3;
        this.feature4 = feature4;
        this.label = label;
    }

    // Getters for the features and label
    public String getFeature1() { 
        return feature1; 
    }
    public String getFeature2() { 
        return feature2; 
    }
    public String getFeature3() { 
        return feature3; 
    }
    public String getFeature4() { 
        return feature4; 
    }
    public String getLabel() { 
        return label; 
    }

    // Override the toString method to print the data instance
    @Override
    public String toString() {
        return "DataInstance [feature1=" + feature1 + ", feature2=" + feature2 + ", feature3=" + feature3 + ", feature4=" + feature4 + ", label=" + label + "]";
    }
}
