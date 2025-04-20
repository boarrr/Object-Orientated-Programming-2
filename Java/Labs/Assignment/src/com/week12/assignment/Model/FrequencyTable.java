/*
 * FrequencyTable.java will be used to store the frequency of each feature in the dataset
 * This will be used for the Naive Bayes Classifier
 * 
 * Author: Ryan Pitman
 * Date: 20-04-2025
 */

package com.week12.assignment.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class FrequencyTable {
    private Map<String, Integer> table = new HashMap<>();

    /**
     * Creates a frequency table from a given list of data instances.
     * 
     * @param dataList the list of DataInstance objects to analyze
     * @see DataInstance
     */
    public void createFrequencyTable(List<DataInstance> dataList) {
        // Initialize the hash map for the frequency table
        table = new HashMap<>();
        
        // Process each data instance in the dataset
        for (DataInstance instance : dataList) {
            String label = instance.getLabel();
            
            // Increment the count for the label
            // Example: If label="yes", key="label=yes" then increment count by 1
            increment("label=" + label);
            
            // Increment the count for the feature
            // Example: If feature1="Day" and label="yes", key="f1=Day|label=yes" then increment count by 1
            increment("f1=" + instance.getFeature1() + "|label=" + label);
            increment("f2=" + instance.getFeature2() + "|label=" + label);
            increment("f3=" + instance.getFeature3() + "|label=" + label);
            increment("f4=" + instance.getFeature4() + "|label=" + label);
        }
    }
    
    // Increments the count for the given key in the frequency table
    private void increment(String key) {
        table.put(key, table.getOrDefault(key, 0) + 1);
    }

    /**
     * Gets the count for a specific key in the frequency table.
     * 
     * @param key the key to look up
     * @return the count for the key, or 0 if the key doesn't exist
     */
    public int getCount(String key) {
        return table.getOrDefault(key, 0);
    }

    /**
     * Gets the count for a specific label in the frequency table.
     * 
     * @param label the label to look up
     * @return the count for the label, or 0 if the label doesn't exist
     */
    public int getLabelCount(String label) {
        return getCount("label=" + label);
    }

    /**
     * Gets the count for a specific feature value given a label in the frequency table.
     * Example: If featureNum=1, featureValue="Day", label="yes" then the key is "f1=Day|label=yes"
     * 
     * @param featureNum the feature number to look up
     * @param featureValue the feature value to look up
     * @param label the label to look up
     * @return the count for the feature value given the label, or 0 if the feature value doesn't exist
     */
    public int getFeatureGivenLabelCount(int featureNum, String featureValue, String label) {
        return getCount("f" + featureNum + "=" + featureValue + "|label=" + label);
    }
}
