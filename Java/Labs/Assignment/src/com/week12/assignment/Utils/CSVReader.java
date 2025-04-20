/*
 * CSVReader.java is a class that reads the CSV file inputted and stores the information in data instances
 * This class will be used to load the data from the CSV file into the data instances and then return the data instances
 * 
 * Author: Ryan Pitman
 * Date: 20-04-2025
 */

package com.week12.assignment.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.week12.assignment.Model.DataInstance;

public class CSVReader {


    public static List<DataInstance> readCSV(String filePath) {
        /**
         * This method reads the CSV file and returns a list of data instances
         * 
         * @param filePath the path to the CSV file
         * @return a list of data instances
         */
        
        List<DataInstance> dataList = new ArrayList<>();

        // Read the CSV file using the BufferedReader
        // https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Skip the header line of the CSV file
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {

                    // Trim all values
                    for (int i = 0; i < values.length; i++) {
                        values[i] = values[i].trim();
                    }
                    
                    // Add the data instance to the list
                    dataList.add(new DataInstance(
                            values[0],
                            values[1],
                            values[2],
                            values[3],
                            values[4]
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Return the list of data instances
        return dataList;
    }
}
