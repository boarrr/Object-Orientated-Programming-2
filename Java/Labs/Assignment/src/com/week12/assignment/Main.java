package com.week12.assignment;

import java.util.List;

import com.week12.assignment.Model.DataInstance;
import com.week12.assignment.Utils.CSVReader;

public class Main {
    public static void main(String[] args) {
        // Read the CSV file
        List<DataInstance> dataList = CSVReader.readCSV("phone_charging_dataset.csv");

        // Print the number of data entries for testing
        System.out.println("Data entries: " + dataList.size());

        // Print for testing
        for (DataInstance data : dataList) {
            System.out.println(data);
        }
    }
}
