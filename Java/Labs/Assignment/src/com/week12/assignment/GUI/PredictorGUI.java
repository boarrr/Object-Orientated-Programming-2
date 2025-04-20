package com.week12.assignment.GUI;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.Map;

import com.week12.assignment.Model.NaiveBayes;

public class PredictorGUI extends JFrame {
    private JComboBox<String> timeOfDayComboBox;
    private JComboBox<String> chargerConnectedComboBox;
    private JComboBox<String> screenOnComboBox;
    private JComboBox<String> batteryLevelComboBox;
    private JLabel resultLabel;
    private JProgressBar probabilityBar;
    private JLabel probabilityLabel;
    
    private NaiveBayes classifier;
    private final Color HEADER_COLOR = new Color(50, 100, 150);
    private final Color BG_COLOR = new Color(240, 240, 250);
    private final Color ACCENT_COLOR = new Color(100, 180, 220);
    private final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 12);

    /**
     * Constructor for the PredictorGUI
     * 
     * @param classifier the trained Naive Bayes classifier
     */
    public PredictorGUI(NaiveBayes classifier) {
        this.classifier = classifier;
        setupUI();
    }
    
    // Set up the GUI
    private void setupUI() {
        // Set window title and size
        setTitle("Phone Charging Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Create the main content panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BG_COLOR);
        
        mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
        mainPanel.add(createInputPanel(), BorderLayout.CENTER);
        mainPanel.add(createResultPanel(), BorderLayout.SOUTH);
        
        // Add the main panel to the frame
        setContentPane(mainPanel);
        
        setVisible(true);
    }
    
    /**
     * Create the title panel
     * 
     * @return the title panel
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(BG_COLOR);
        
        JLabel titleLabel = new JLabel("Naive Bayes Phone Charging Predictor");
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(HEADER_COLOR);
        
        titlePanel.add(titleLabel);
        return titlePanel;
    }
    
    /**
     * Create the input panel
     * 
     * @return the input panel
     */
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 1, 10, 15));
        inputPanel.setBackground(BG_COLOR);
        inputPanel.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(HEADER_COLOR), "Input Features"),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Time of Day
        timeOfDayComboBox = new JComboBox<>(new String[] {"Day", "Night"});
        inputPanel.add(createFeaturePanel("Time of Day:", timeOfDayComboBox));
        
        // Charger Connected
        chargerConnectedComboBox = new JComboBox<>(new String[] {"Yes", "No"});
        inputPanel.add(createFeaturePanel("Charger Connected:", chargerConnectedComboBox));
        
        // Screen On
        screenOnComboBox = new JComboBox<>(new String[] {"Yes", "No"});
        inputPanel.add(createFeaturePanel("Screen On:", screenOnComboBox));
        
        // Battery Level
        batteryLevelComboBox = new JComboBox<>(new String[] {"High", "Low"});
        inputPanel.add(createFeaturePanel("Battery Level:", batteryLevelComboBox));
        
        return inputPanel;
    }
    
    /**
     * Create a feature panel with a label and combo box
     * 
     * @param labelText the label text
     * @param comboBox the combo box
     * @return the feature panel
     */
    private JPanel createFeaturePanel(String labelText, JComboBox<String> comboBox) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(BG_COLOR);
        
        JLabel label = new JLabel(labelText);
        label.setFont(HEADER_FONT);
        label.setForeground(HEADER_COLOR);
        
        comboBox.setFont(REGULAR_FONT);
        comboBox.setBackground(Color.WHITE);
        
        panel.add(label, BorderLayout.WEST);
        panel.add(comboBox, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Create the result panel
     * 
     * @return the result panel
     */
    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout(10, 15));
        resultPanel.setBackground(BG_COLOR);
        resultPanel.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(HEADER_COLOR), "Prediction Results"),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Create the predict button
        JButton predictButton = new JButton("Predict");
        predictButton.setFont(HEADER_FONT);
        predictButton.setBackground(ACCENT_COLOR);
        predictButton.setForeground(Color.BLACK);
        predictButton.setFocusPainted(true);
        predictButton.addActionListener(e -> predict());
        
        // Create the result displays
        JPanel displayPanel = new JPanel(new GridLayout(3, 1, 5, 10));
        displayPanel.setBackground(BG_COLOR);
        
        resultLabel = new JLabel("Prediction: ");
        resultLabel.setFont(HEADER_FONT);
        
        probabilityLabel = new JLabel("Probability: ");
        probabilityLabel.setFont(REGULAR_FONT);
        
        probabilityBar = new JProgressBar(0, 100);
        probabilityBar.setStringPainted(true);
        probabilityBar.setFont(REGULAR_FONT);
        probabilityBar.setValue(0);
        probabilityBar.setForeground(ACCENT_COLOR);
        
        displayPanel.add(resultLabel);
        displayPanel.add(probabilityLabel);
        displayPanel.add(probabilityBar);
        
        // Add a panel for the predict button with some padding
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BG_COLOR);
        buttonPanel.add(predictButton);
        
        resultPanel.add(buttonPanel, BorderLayout.NORTH);
        resultPanel.add(displayPanel, BorderLayout.CENTER);
        
        return resultPanel;
    }
    
    /**
     * Predict the charging status based on the user's input and the classifier
     * @see NaiveBayes
     */
    private void predict() {
        String timeOfDay = (String) timeOfDayComboBox.getSelectedItem();
        String chargerConnected = (String) chargerConnectedComboBox.getSelectedItem();
        String screenOn = (String) screenOnComboBox.getSelectedItem();
        String batteryLevel = (String) batteryLevelComboBox.getSelectedItem();

        // Map the probabilities to the prediction
        Map<String, Double> probabilities = classifier.calculateProbabilities(
            timeOfDay, chargerConnected, screenOn, batteryLevel);
        String prediction = classifier.predict(
            timeOfDay, chargerConnected, screenOn, batteryLevel);
        
        // Format the prediction result
        if (prediction.equals("yes")) {
            resultLabel.setText("Prediction: Phone is charging");
        } else {
            resultLabel.setText("Prediction: Phone is not charging");
        }
        
        // Display probability information
        double probability = probabilities.get(prediction) * 100;
        probabilityLabel.setText(String.format("Probability: %.2f%%", probability));
        probabilityBar.setValue((int) probability);
        
        // Set color based on prediction
        if (prediction.equals("yes")) {
            resultLabel.setForeground(new Color(50, 150, 50));
            probabilityBar.setForeground(new Color(50, 150, 50));
        } else {
            resultLabel.setForeground(new Color(150, 50, 50));
            probabilityBar.setForeground(new Color(150, 50, 50));
        }
    }
}
