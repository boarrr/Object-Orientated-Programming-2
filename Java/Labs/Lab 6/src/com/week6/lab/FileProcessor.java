package com.week6.lab;

import java.io.*;
import java.util.ArrayList;

public class FileProcessor {
    private String filename;
    
    public FileProcessor(String filename) {
        this.filename = filename;
    }
    
    // Reads the file and returns its non-empty lines as a String array.
    public String[] readFile() {
        // Create an ArrayList to store the lines
        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;

            // Read each line and add it to the ArrayList
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }

            // Close the reader
            reader.close();
        } catch (IOException e) { // Catch any exceptions
            System.out.println("Error reading file: " + filename);
            System.out.println(e.getMessage());
        }

        // Return the lines as a String array
        return lines.toArray(new String[lines.size()]);
    }
    
    // Writes a single line of text to the file.
    public void writeLine(String text) {
        try {
            // Create a PrintWriter object to write to the file
            PrintWriter writer = new PrintWriter(new FileWriter(filename, true));

            // Write the text to the file and close it
            writer.println(text);
            writer.close();
        } catch (IOException e) { // Catch any exceptions
            System.out.println("Error writing to file: " + filename);
        }
    }
}
