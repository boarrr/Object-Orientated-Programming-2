package com.week12.assignment.gui;

import javax.swing.*;
import javax.swing.border.*;

import com.week12.assignment.model.DataInstance;
import com.week12.assignment.model.NaiveBayes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

/**
 * Panel for the Data Entry tab that allows users to add new data to the dataset
 * This tab will allow the user to recalculate the model with the updated dataset
 * @see NaiveBayes
 */
public class DataEntryPanel extends JPanel {
    private JComboBox<String> timeOfDayInput;
    private JComboBox<String> chargerConnectedInput;
    private JComboBox<String> screenOnInput;
    private JComboBox<String> batteryLevelInput;
    private JComboBox<String> phoneIsChargingInput;
    private JButton addDataButton;
    private JButton recalculateButton;
    private JLabel statusLabel;
    
    private List<DataInstance> dataList;
    private NaiveBayes classifier;
    private String csvFilePath;
    
    // Colours and fonts for the GUI
    private final Color HEADER_COLOR = new Color(50, 100, 150);
    private final Color BG_COLOR = new Color(240, 240, 250);
    private final Color SUCCESS_COLOR = new Color(50, 150, 50);
    private final Color WARNING_COLOR = new Color(200, 120, 50);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 12);
    
    /**
     * Constructor for the DataEntryPanel.
     * 
     * @param dataList the dataset
     * @param classifier the Naive Bayes classifier
     * @param csvFilePath the path to the CSV file
     */
    public DataEntryPanel(List<DataInstance> dataList, NaiveBayes classifier, String csvFilePath) {
        this.dataList = dataList;
        this.classifier = classifier;
        this.csvFilePath = csvFilePath;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(BG_COLOR);
        
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createDataEntryPanel(), BorderLayout.CENTER);
    }
    
    /**
     * Creates the title panel.
     * 
     * @return the title panel
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(BG_COLOR);
        
        JLabel titleLabel = new JLabel("Dataset Entry and Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(HEADER_COLOR);
        
        statusLabel = new JLabel("Ready to add new data entries", SwingConstants.CENTER);
        statusLabel.setFont(REGULAR_FONT);
        statusLabel.setForeground(HEADER_COLOR);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(statusLabel, BorderLayout.SOUTH);
        
        return titlePanel;
    }
    
    /**
     * Creates the data entry panel.
     * 
     * @return the data entry panel
     */
    private JPanel createDataEntryPanel() {
        JPanel entryPanel = new JPanel(new BorderLayout(10, 10));
        entryPanel.setBackground(BG_COLOR);
        entryPanel.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(HEADER_COLOR), "Add New Data Entry"),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Create the input fields panel
        JPanel inputsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputsPanel.setBackground(BG_COLOR);
        
        // Time of Day
        JLabel timeOfDayLabel = new JLabel("Time of Day:");
        timeOfDayLabel.setFont(REGULAR_FONT);
        timeOfDayInput = new JComboBox<>(new String[] {"Day", "Night"});
        timeOfDayInput.setFont(REGULAR_FONT);
        inputsPanel.add(timeOfDayLabel);
        inputsPanel.add(timeOfDayInput);
        
        // Charger Connected
        JLabel chargerLabel = new JLabel("Charger Connected:");
        chargerLabel.setFont(REGULAR_FONT);
        chargerConnectedInput = new JComboBox<>(new String[] {"Yes", "No"});
        chargerConnectedInput.setFont(REGULAR_FONT);
        inputsPanel.add(chargerLabel);
        inputsPanel.add(chargerConnectedInput);
        
        // Screen On
        JLabel screenLabel = new JLabel("Screen On:");
        screenLabel.setFont(REGULAR_FONT);
        screenOnInput = new JComboBox<>(new String[] {"On", "Off"});
        screenOnInput.setFont(REGULAR_FONT);
        inputsPanel.add(screenLabel);
        inputsPanel.add(screenOnInput);
        
        // Battery Level
        JLabel batteryLabel = new JLabel("Battery Level:");
        batteryLabel.setFont(REGULAR_FONT);
        batteryLevelInput = new JComboBox<>(new String[] {"High", "Low"});
        batteryLevelInput.setFont(REGULAR_FONT);
        inputsPanel.add(batteryLabel);
        inputsPanel.add(batteryLevelInput);
        
        // Phone Is Charging
        JLabel chargingLabel = new JLabel("Phone Is Charging:");
        chargingLabel.setFont(REGULAR_FONT);
        phoneIsChargingInput = new JComboBox<>(new String[] {"yes", "no"});
        phoneIsChargingInput.setFont(REGULAR_FONT);
        inputsPanel.add(chargingLabel);
        inputsPanel.add(phoneIsChargingInput);
        
        // Create buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(BG_COLOR);
        
        addDataButton = new JButton("Add Data Entry");
        addDataButton.setFont(HEADER_FONT);
        addDataButton.setBackground(new Color(50, 150, 50));
        addDataButton.setForeground(Color.WHITE);
        addDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDataEntry();
            }
        });
        
        recalculateButton = new JButton("Recalculate Model");
        recalculateButton.setFont(HEADER_FONT);
        recalculateButton.setBackground(new Color(80, 80, 200));
        recalculateButton.setForeground(Color.WHITE);
        recalculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recalculateModel();
            }
        });
        
        buttonsPanel.add(addDataButton);
        buttonsPanel.add(recalculateButton);
        
        // Add instructions label
        JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
        infoPanel.setBackground(BG_COLOR);
        
        JLabel infoLabel = new JLabel("<html>Use this form to add new data entries to the dataset.<br>After adding entries, click 'Recalculate Model' to update the model with the new data.</html>");
        infoLabel.setFont(REGULAR_FONT);
        infoLabel.setForeground(HEADER_COLOR);
        infoLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        infoPanel.add(infoLabel, BorderLayout.CENTER);
        
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(BG_COLOR);
        contentPanel.add(infoPanel, BorderLayout.NORTH);
        contentPanel.add(inputsPanel, BorderLayout.CENTER);
        contentPanel.add(buttonsPanel, BorderLayout.SOUTH);
        
        // Center the content on the screen
        JPanel centeringPanel = new JPanel(new GridBagLayout());
        centeringPanel.setBackground(BG_COLOR);
        centeringPanel.add(contentPanel);
        
        entryPanel.add(centeringPanel, BorderLayout.CENTER);
        
        return entryPanel;
    }
    
    /**
     * Adds a new data entry to the dataset and CSV file.
     */
    private void addDataEntry() {
        try {
            // Get values from input fields
            String timeOfDay = (String) timeOfDayInput.getSelectedItem();
            String chargerConnected = (String) chargerConnectedInput.getSelectedItem();
            String screenOn = (String) screenOnInput.getSelectedItem();
            String batteryLevel = (String) batteryLevelInput.getSelectedItem();
            String phoneIsCharging = (String) phoneIsChargingInput.getSelectedItem();
            
            // Create new data instance and add to dataset
            DataInstance newInstance = new DataInstance(timeOfDay, chargerConnected, screenOn, batteryLevel, phoneIsCharging);
            dataList.add(newInstance);
            
            // Add to CSV file
            appendToCSV(timeOfDay, chargerConnected, screenOn, batteryLevel, phoneIsCharging);
            
            // Update status
            statusLabel.setText("Data entry added successfully! Dataset now has " + dataList.size() + " entries.");
            statusLabel.setForeground(SUCCESS_COLOR);
            
        } catch (Exception ex) {
            statusLabel.setText("Error adding data: " + ex.getMessage());
            statusLabel.setForeground(WARNING_COLOR);
            ex.printStackTrace();
        }
    }
    
    /**
     * Recalculates the model with the updated dataset.
     */
    private void recalculateModel() {
        try {
            statusLabel.setText("Recalculating model...");
            statusLabel.setForeground(HEADER_COLOR);
            
            // Use SwingWorker to avoid freezing the UI during training
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    // Retrain the classifier with the updated dataset
                    classifier.train(dataList);
                    return null;
                }
                
                @Override
                protected void done() {
                    // Update status when done
                    statusLabel.setText("Model recalculated successfully with " + dataList.size() + " entries!");
                    statusLabel.setForeground(SUCCESS_COLOR);
                }
            };
            
            worker.execute();
            
        } catch (Exception ex) {
            statusLabel.setText("Error recalculating model: " + ex.getMessage());
            statusLabel.setForeground(WARNING_COLOR);
            ex.printStackTrace();
        }
    }
    
    /**
     * Appends a new data entry to the CSV file.
     * 
     * @param timeOfDay - The time of day
     * @param chargerConnected - Whether the charger is connected
     * @param screenOn - Whether the screen is on
     * @param batteryLevel - The battery level
     * @param phoneIsCharging - Whether the phone is charging
     * @throws IOException - If the file is not found or cannot be written to
     */
    private void appendToCSV(String timeOfDay, String chargerConnected, String screenOn, 
        String batteryLevel, String phoneIsCharging) throws IOException {
            try (FileWriter fw = new FileWriter(csvFilePath, true);

            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {

            // Format: TimeOfDay,ChargerConnected,ScreenOn,BatteryLevel,PhoneIsCharging
            out.write(System.lineSeparator()); // Ensures newline
            out.write(timeOfDay + "," + chargerConnected + "," + screenOn + "," + batteryLevel + "," + phoneIsCharging);
            out.flush();

        }
    }
} 