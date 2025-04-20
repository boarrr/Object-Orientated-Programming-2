package com.week12.assignment;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import com.week12.assignment.Model.DataInstance;
import com.week12.assignment.Model.NaiveBayes;
import com.week12.assignment.Utils.CSVReader;

public class Main {
    public static void main(String[] args) {
        // Read the CSV file
        List<DataInstance> dataList = CSVReader.readCSV("charging_dataset.csv");

        // Shuffle the data to randomize it
        Collections.shuffle(dataList);
        
        // Split the data into training and test sets (80% training, 20% testing)
        int trainSize = (int) (dataList.size() * 0.8);
        List<DataInstance> trainData = new ArrayList<>(dataList.subList(0, trainSize));
        List<DataInstance> testData = new ArrayList<>(dataList.subList(trainSize, dataList.size()));
        
        // Create and train the Naive Bayes classifier
        NaiveBayes classifier = new NaiveBayes();
        classifier.train(trainData);
        
        // Evaluate the classifier on the test data
        double accuracy = classifier.evaluate(testData);
        System.out.println("Classifier accuracy: " + String.format("%.2f", accuracy) + "%");  
    }
}
