package com.week12.assignment;

import java.util.List;
import javax.swing.SwingUtilities;
import java.io.InputStream;
import java.io.FileInputStream;

import com.week12.assignment.utils.CSVReader;
import com.week12.assignment.gui.PredictorGUI;
import com.week12.assignment.model.DataInstance;
import com.week12.assignment.model.NaiveBayes;

/**
 * Main class for the Naive Bayes Predictor.
 * Initializes the application and creates the GUI.
 * 
 * @author Ryan Pitman
 * @throws Exception if the CSV file is not found or cannot be read
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Use relative path to the CSV file
            String csvFilePath = "src/com/week12/assignment/data/charging_dataset.csv";  

            // Read CSV from file input stream
            InputStream inputStream = new FileInputStream(csvFilePath);
            List<DataInstance> dataList = CSVReader.readCSV(inputStream);

            // Create the Naive Bayes classifier
            NaiveBayes classifier = new NaiveBayes();

            // Create and show the GUI
            SwingUtilities.invokeLater(() -> {
                new PredictorGUI(classifier, dataList, csvFilePath);
            });

        } catch (Exception e) {
            System.err.println("Error initializing application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
