package com.week12.assignment.gui;

import javax.swing.*;
import javax.swing.border.*;

import com.week12.assignment.model.DataInstance;
import com.week12.assignment.model.NaiveBayes;

import java.awt.*;
import java.util.Map;
import java.util.List;

/**
 * PredictorGUI class extends JFrame to create the GUI for the Naive Bayes Predictor.
 * Uses a tabbed pane to organise the different functionalities of the program.
 * The Predictor tab contains the functionality to make predictions.
 * The Data Entry tab allows the user to add new data instances to the training dataset.
 * The Evaluation tab allows the user to assess the classifier's performance using a stratified train-test split.
 * 
 * @author Ryan Pitman
 */
public class PredictorGUI extends JFrame {
    private JComboBox<String> timeOfDayComboBox;
    private JComboBox<String> chargerConnectedComboBox;
    private JComboBox<String> screenOnComboBox;
    private JComboBox<String> batteryLevelComboBox;
    private JLabel resultLabel;
    private JProgressBar probabilityBar;
    private JLabel probabilityLabel;
    private JLabel trainingStatusLabel;
    private JButton predictButton;
    
    private NaiveBayes classifier;
    private List<DataInstance> dataList;
    private String csvFilePath;
    
    private final Color HEADER_COLOR = new Color(50, 100, 150);
    private final Color BG_COLOR = new Color(240, 240, 250);
    private final Color ACCENT_COLOR = new Color(100, 180, 220);
    private final Color SUCCESS_COLOR = new Color(50, 150, 50);
    private final Color WARNING_COLOR = new Color(200, 120, 50);
    private final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 12);

    /**
     * Constructor for the PredictorGUI
     * 
     * @param classifier the untrained Naive Bayes classifier
     * @param dataList the dataset to be used for training
     * @param csvFilePath the path to the CSV file
     */
    public PredictorGUI(NaiveBayes classifier, List<DataInstance> dataList, String csvFilePath) {
        this.classifier = classifier;
        this.dataList = dataList;
        this.csvFilePath = csvFilePath;
        setupUI();
    }
    
    /**
     * Sets up the GUI for the program, sets title and size of the window.
     * Creates a tabbed pane to organise the different functionalities of the program.
     */
    private void setupUI() {
        // Set window title and size
        setTitle("Phone Charging Predictor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 600);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Create a tabbed pane for multiple levels of functionality
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(HEADER_FONT);
        
        // Add tabs
        tabbedPane.addTab("Predictor", createPredictorPanel());
        tabbedPane.addTab("Data Entry", new DataEntryPanel(dataList, classifier, csvFilePath));
        tabbedPane.addTab("Evaluation", new EvaluationPanel(dataList));
        
        // Add the tabbed pane to the frame
        add(tabbedPane);
        
        setVisible(true);
    }
    
    /**
     * Creates the Predictor tab panel that contains all prediction functionality for the program
     * 
     * @return the panel containing the prediction components
     */
    private JPanel createPredictorPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BG_COLOR);
        
        mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
        mainPanel.add(createInputPanel(), BorderLayout.CENTER);
        mainPanel.add(createResultPanel(), BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    /**
     * Creates the title panel of the program.
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
     * Creates the input panel of the predictor tab.
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
        screenOnComboBox = new JComboBox<>(new String[] {"On", "Off"});
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
     * Creates the result panel for the predictor tab.
     * Adds the prediction results and buttons to the panel.
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
        
        // Create the buttons
        predictButton = new JButton("Predict");
        predictButton.setFont(HEADER_FONT);
        predictButton.setBackground(ACCENT_COLOR);
        predictButton.setForeground(Color.BLACK);
        predictButton.setFocusPainted(true);
        predictButton.addActionListener(e -> predict());
        predictButton.setEnabled(false); // Disabled until model is trained
        
        JButton trainButton = new JButton("Train Model");
        trainButton.setFont(HEADER_FONT);
        trainButton.setBackground(new Color(80, 160, 80));
        trainButton.setForeground(Color.WHITE);
        trainButton.setFocusPainted(true);
        trainButton.addActionListener(e -> trainModel());
        
        // Create the result displays
        JPanel displayPanel = new JPanel(new GridLayout(4, 1, 5, 10));
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
        
        trainingStatusLabel = new JLabel("Model is not trained. Please train the model first.");
        trainingStatusLabel.setFont(REGULAR_FONT);
        trainingStatusLabel.setForeground(WARNING_COLOR);
        
        displayPanel.add(resultLabel);
        displayPanel.add(probabilityLabel);
        displayPanel.add(probabilityBar);
        displayPanel.add(trainingStatusLabel);
        
        // Add a panel for the buttons with some padding
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        buttonPanel.setBackground(BG_COLOR);
        buttonPanel.add(predictButton);
        buttonPanel.add(trainButton);
        
        resultPanel.add(buttonPanel, BorderLayout.NORTH);
        resultPanel.add(displayPanel, BorderLayout.CENTER);
        
        return resultPanel;
    }
    
    /**
     * Trains the model with the loaded dataset, utilises SwingWorker to prevent freezing
     * Runs the training in the background and updates the status label when done
     * @see SwingWorker
     * @throws Exception if the training fails or the dataset is empty
     */
    private void trainModel() {
        // Update the status label
        trainingStatusLabel.setText("Training model...");
        trainingStatusLabel.setForeground(HEADER_COLOR);
        
        // Use SwingWorker to avoid freezing the UI during training
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                // Train the classifier
                classifier.train(dataList);
                return null;
            }
            
            @Override
            protected void done() {
                // Update the status label when done, showing dataset size
                trainingStatusLabel.setText("Model trained successfully with " + dataList.size() + " data instances!");
                trainingStatusLabel.setForeground(SUCCESS_COLOR);
                
                // Enable the predict button now that the model is trained
                predictButton.setEnabled(true);
                
                // Clear the previous prediction
                resultLabel.setText("Prediction: ");
                probabilityLabel.setText("Probability: ");
                probabilityBar.setValue(0);
                probabilityBar.setForeground(ACCENT_COLOR);
            }
        };
        
        worker.execute();
    }
    
    /**
     * Predicts the charging status based on the user's input and the classifier
     * @see NaiveBayes
     * @throws Exception if the model is not trained, prompts the user to train the model first
     */
    private void predict() {
        if (!classifier.isTrained()) {
            JOptionPane.showMessageDialog(this, 
                "Please train the model first by clicking the 'Train Model' button.", 
                "Model Not Trained", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
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
        
        // Display the probability information
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
