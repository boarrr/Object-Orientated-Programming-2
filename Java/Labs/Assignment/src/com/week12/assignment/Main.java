package com.week12.assignment;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingUtilities;

import com.week12.assignment.Model.DataInstance;
import com.week12.assignment.Model.NaiveBayes;
import com.week12.assignment.Utils.CSVReader;
import com.week12.assignment.GUI.PredictorGUI;

public class Main {
    public static void main(String[] args) {
        // Read the CSV file
        List<DataInstance> dataList = CSVReader.readCSV("charging_dataset.csv");
        
        // Create and train the Naive Bayes classifier
        NaiveBayes classifier = new NaiveBayes();
        classifier.train(dataList);
        
        // Create and show the GUI
        SwingUtilities.invokeLater(() -> {
            new PredictorGUI(classifier);
        });
    }
}
