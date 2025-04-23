/*
 * NaiveBayes.java implements the Naive Bayes classifier algorithm for this assignment
 * It utilise the frequency table to calculate probabilities and make predictions.
 * 
 * Author: Ryan Pitman
 * Date: 20-04-2025
 */

package com.week12.assignment.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class NaiveBayes {
    private FrequencyTable frequencyTable;
    private boolean isTrained = false;
    
    /**
     * Constructor initializes the frequency table.
     */
    public NaiveBayes() {
        this.frequencyTable = new FrequencyTable();
    }
    
    /**
     * Trains the classifier on a given dataset by building the frequency table.
     * 
     * @param dataList the list of data instances to train on
     */
    public void train(List<DataInstance> dataList) {
        // Build the frequency table from the training data
        frequencyTable.createFrequencyTable(dataList);
        isTrained = true;
    }
    
    /**
     * Predicts the class label for a new instance based on the four features.
     * 
     * @param feature1 value of the first feature
     * @param feature2 value of the second feature
     * @param feature3 value of the third feature
     * @param feature4 value of the fourth feature
     * @return the predicted class label
     * @throws IllegalStateException if the classifier has not been trained yet
     *
     */
    public String predict(String feature1, String feature2, String feature3, String feature4) {
        // Calculate the probabilities of each class for the new instance and store them in a map
        Map<String, Double> probabilities = calculateProbabilities(feature1, feature2, feature3, feature4);
        
        // Variable to store the class with the highest probability and the highest probability value
        String bestLabel = null;
        double bestProb = -1;
        
        // Iterate through the map and find the class with the highest probability
        for (Map.Entry<String, Double> entry : probabilities.entrySet()) {
            if (entry.getValue() > bestProb) {
                bestProb = entry.getValue();
                bestLabel = entry.getKey();
            }
        }
        
        // Return the class with the highest probability
        return bestLabel;
    }
    
    /**
     * Calculates the probability of each class for a new instance.
     * 
     * @param feature1 value of the first feature
     * @param feature2 value of the second feature
     * @param feature3 value of the third feature
     * @param feature4 value of the fourth feature
     * @return a map of class labels to their probabilities
     * @throws IllegalStateException if the classifier has not been trained
     */
    public Map<String, Double> calculateProbabilities(String feature1, String feature2, String feature3, String feature4) {
        if (!isTrained) {
            throw new IllegalStateException("Make sure to train the classifier before predicting!");
        }
        
        // Get the possible class labels from our frequency data, in this case only yes and no
        String[] possibleLabels = {"yes", "no"};
        
        // Store the probabilities for each class in a map
        Map<String, Double> probabilities = new HashMap<>();
        
        // Calculate probability for each possible class
        for (String label : possibleLabels) {
            // Calculate the prior probability of the class
            // Probability of a label is the frequency of this label divided by total instances
            
            double prior = (double) frequencyTable.getLabelCount(label) / 
                          (frequencyTable.getLabelCount("yes") + frequencyTable.getLabelCount("no"));
            
            // Calculate the conditional probabilities for each feature
            // Implemented Laplace smoothing to avoid zero probabilities
            // https://towardsdatascience.com/laplace-smoothing-in-naive-bayes-algorithm-9c237a8bdece/
            double condProb1 = (frequencyTable.getFeatureGivenLabelCount(1, feature1, label) + 1.0) / 
                   (frequencyTable.getLabelCount(label) + 2.0);

            double condProb2 = (frequencyTable.getFeatureGivenLabelCount(2, feature2, label) + 1.0) / 
                            (frequencyTable.getLabelCount(label) + 2.0);

            double condProb3 = (frequencyTable.getFeatureGivenLabelCount(3, feature3, label) + 1.0) / 
                            (frequencyTable.getLabelCount(label) + 2.0);

            double condProb4 = (frequencyTable.getFeatureGivenLabelCount(4, feature4, label) + 1.0) / 
                            (frequencyTable.getLabelCount(label) + 2.0);
    
            // Calculate the final probability using Bayes' theorem
            // https://www.geeksforgeeks.org/bayes-theorem/?ref=rp
            double probability = prior * condProb1 * condProb2 * condProb3 * condProb4;
            
            // Store the probability for this class
            probabilities.put(label, probability);
        }
    
        // Ensure the probabilities add up to 1
        double total = probabilities.values().stream().mapToDouble(Double::doubleValue).sum();    
        if (total > 0) {
            for (String label : probabilities.keySet()) {
                probabilities.put(label, probabilities.get(label) / total);
            }
        }
        
        return probabilities;
    }
    
    /**
     * Evaluates the classifier on a test dataset.
     * 
     * @param testData the list of data instances to test on
     * @return the accuracy of the classifier (percentage of correct predictions)
     * @throws IllegalStateException if the classifier has not been trained
     */
    public double evaluate(List<DataInstance> testData) {
        if (!isTrained) {
            throw new IllegalStateException("Make sure to train the classifier before evaluating!");
        }
        
        // Check if the test data is valid
        if (testData == null || testData.isEmpty()) {
            return 0.0;
        }

        // Variable to store the number of correct predictions
        int correct = 0;

        // Iterate through the test data and make predictions
        for (DataInstance instance : testData) {
            String prediction = predict(
                instance.getFeature1(), 
                instance.getFeature2(), 
                instance.getFeature3(), 
                instance.getFeature4()
            );
            
            if (prediction.equals(instance.getLabel())) {
                correct++;
            }
        }

        // Return the accuracy of the classifier as a percentage
        return (double) correct / testData.size() * 100.0;
    }
    
    /**
     * Gets the frequency table used by this classifier.
     * 
     * @return the frequency table
     */
    public FrequencyTable getFrequencyTable() {
        return frequencyTable;
    }
    
    /**
     * Checks if the classifier has been trained.
     * 
     * @return true if the classifier has been trained
     */
    public boolean isTrained() {
        return isTrained;
    }
}
