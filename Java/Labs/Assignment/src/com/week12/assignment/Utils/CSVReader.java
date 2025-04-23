/*
 * CSVReader.java is a class that reads the CSV file inputted and stores the information in data instances
 * This class will be used to load the data from the CSV file into the data instances and then return the data instances
 * 
 * Author: Ryan Pitman
 * Date: 20-04-2025
 */

 package com.week12.assignment.utils;

 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;

import com.week12.assignment.model.DataInstance;
 
public class CSVReader {
    
    /**
     * Reads a CSV file from the input stream, previously loaded from the file path
     * @param inputStream - The input stream of the CSV file
     * @return - An array list of data instances
     * @throws IOException - If the file is not found or cannot be read
     */
    public static List<DataInstance> readCSV(InputStream inputStream) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            return parseCSV(br);
        } catch (IOException e) {
            System.err.println("Error reading CSV input stream: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Parses the opeend CSV file and returns the array list of data instances
     * @param br - The buffered reader of the CSV file
     * @return - A list of data instances
     * @throws IOException - If the file is not found or cannot be read for any reason
     */
    private static List<DataInstance> parseCSV(BufferedReader br) throws IOException {
        List<DataInstance> dataList = new ArrayList<>();
        String line;

        br.readLine(); 
        
        // While the line is not null, split the line by commas and add the data instance to the list
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");

            if (values.length == 5) {
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }

                dataList.add(new DataInstance(
                    values[0],
                    values[1],
                    values[2],
                    values[3],
                    values[4]
                ));
            }
        }

        return dataList;
    }
}
 