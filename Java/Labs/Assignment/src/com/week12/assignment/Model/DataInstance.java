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

    /**
     * Constructor for the DataInstance class.
     * 
     * @param feature1 the first feature
     * @param feature2 the second feature
     * @param feature3 the third feature
     * @param feature4 the fourth feature
     * @param label the label
     */
    public DataInstance(String feature1, String feature2, String feature3, String feature4, String label) {
        this.feature1 = feature1;
        this.feature2 = feature2;
        this.feature3 = feature3;
        this.feature4 = feature4;
        this.label = label;
    }

    /**
     * Getter for the first feature.
     * 
     * @return the first feature
     */
    public String getFeature1() { 
        return feature1; 
    }

    /**
     * Getter for the second feature.
     * 
     * @return the second feature
     */
    public String getFeature2() { 
        return feature2; 
    }

    /**
     * Getter for the third feature.
     * 
     * @return the third feature
     */
    public String getFeature3() { 
        return feature3; 
    }

    /**
     * Getter for the fourth feature.
     * 
     * @return the fourth feature
     */
    public String getFeature4() { 
        return feature4; 
    }
    
    /**
     * Getter for the label.
     * 
     * @return the label
     */
    public String getLabel() { 
        return label; 
    }

    /**
     * Override the toString method to print the data instance.
     * 
     * @return a string representation of the data instance
     */
    @Override
    public String toString() {
        return "DataInstance [feature1=" + feature1 + ", feature2=" + feature2 + ", feature3=" + feature3 + ", feature4=" + feature4 + ", label=" + label + "]";
    }
}
