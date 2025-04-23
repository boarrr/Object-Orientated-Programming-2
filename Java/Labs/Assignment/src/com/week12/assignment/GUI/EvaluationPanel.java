package com.week12.assignment.gui;

import javax.swing.*;
import javax.swing.border.*;

import com.week12.assignment.model.DataInstance;
import com.week12.assignment.model.NaiveBayes;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Random;

/**
 * Panel for the Evaluation tab that is used to show the accuracy of the model
 * Ensures a stratified split of the data into training and testing sets
 * @see NaiveBayes
 */
public class EvaluationPanel extends JPanel {
    private List<DataInstance> dataList;
    
    private JButton evaluateButton;
    private JLabel statusLabel;
    private JLabel accuracyLabel;
    private JLabel trainSizeLabel;
    private JLabel testSizeLabel;
    private JProgressBar accuracyBar;
    
    // Colours and fonts for the GUI
    private final Color HEADER_COLOR = new Color(50, 100, 150);
    private final Color BG_COLOR = new Color(240, 240, 250);
    private final Color SUCCESS_COLOR = new Color(50, 150, 50);
    private final Color WARNING_COLOR = new Color(200, 120, 50);
    private final Font TITLE_FONT = new Font("Arial", Font.BOLD, 18);
    private final Font HEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private final Font REGULAR_FONT = new Font("Arial", Font.PLAIN, 12);
    
    /**
     * Constructor for the EvaluationPanel
     * 
     * @param dataList the dataset
     * @param classifier the Naive Bayes classifier
     */
    public EvaluationPanel(List<DataInstance> dataList) {
        this.dataList = dataList;
        
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setBackground(BG_COLOR);
        
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createEvaluationPanel(), BorderLayout.CENTER);
    }
    
    /**
     * Creates the title panel.
     * 
     * @return the title panel
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(BG_COLOR);
        
        JLabel titleLabel = new JLabel("Model Evaluation", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(HEADER_COLOR);
        
        statusLabel = new JLabel("Ready to evaluate the model", SwingConstants.CENTER);
        statusLabel.setFont(REGULAR_FONT);
        statusLabel.setForeground(HEADER_COLOR);
        
        titlePanel.add(titleLabel, BorderLayout.NORTH);
        titlePanel.add(statusLabel, BorderLayout.SOUTH);
        
        return titlePanel;
    }
    
    /**
     * Creates the evaluation panel
     * 
     * @return the evaluation panel
     */
    private JPanel createEvaluationPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setBorder(new CompoundBorder(
            new TitledBorder(new LineBorder(HEADER_COLOR), "Model Evaluation"),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Information pane
        JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
        infoPanel.setBackground(BG_COLOR);
        
        // Utilises HTML to format the label to be more readable and easier to understand for the user
        // https://stackoverflow.com/questions/6635730/how-do-i-put-html-in-a-jlabel-in-java
        JLabel infoLabel = new JLabel("<html>This tab evaluates the model accuracy by:<br>"
                + "1. Splitting the data into 75% training and 25% testing<br>"
                + "2. Ensuring stratified sampling<br>"
                + "3. Training the model on the training set<br>"
                + "4. Testing prediction accuracy on the testing set and displaying the results</html>");
        infoLabel.setFont(REGULAR_FONT);
        infoLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        
        infoPanel.add(infoLabel, BorderLayout.NORTH);
        
        // Results pane
        JPanel resultsPanel = new JPanel(new GridLayout(3, 1, 5, 10));
        resultsPanel.setBackground(BG_COLOR);
        
        accuracyLabel = new JLabel("Accuracy: ");
        accuracyLabel.setFont(HEADER_FONT);
        
        accuracyBar = new JProgressBar(0, 100);
        accuracyBar.setStringPainted(true);
        accuracyBar.setFont(REGULAR_FONT);
        accuracyBar.setValue(0);
        accuracyBar.setForeground(HEADER_COLOR);
        
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        statsPanel.setBackground(BG_COLOR);
        
        trainSizeLabel = new JLabel("Training Set Size: ");
        trainSizeLabel.setFont(REGULAR_FONT);
        
        testSizeLabel = new JLabel("Test Set Size: ");
        testSizeLabel.setFont(REGULAR_FONT);
        
        statsPanel.add(trainSizeLabel);
        statsPanel.add(testSizeLabel);
        
        resultsPanel.add(accuracyLabel);
        resultsPanel.add(accuracyBar);
        resultsPanel.add(statsPanel);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BG_COLOR);
        
        evaluateButton = new JButton("Evaluate Model");
        evaluateButton.setFont(HEADER_FONT);
        evaluateButton.setBackground(new Color(80, 160, 80));
        evaluateButton.setForeground(Color.WHITE);
        evaluateButton.addActionListener(e -> evaluateModel());
        
        buttonPanel.add(evaluateButton);
        
        // Combine all panels into the main panel
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(resultsPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        return mainPanel;
    }
    
    /**
     * Evaluates the model by splitting data into training and testing sets,
     * ensuring stratified sampling, and measuring prediction accuracy
     * "" Note that your training and test data should be stratified via your code (have the same proportion of the yes/nos of the label classes in each of training/ testing
     *  So if overall dataset had 60% yes, 40% no, you could accidentally train on 60% yes, and test on all the nos, which would not work. Instead you want
     *  your 60/40 balance of rows in your training, and 60/40 in your testing ""
     */
    private void evaluateModel() {
        // Disable button and update status
        evaluateButton.setEnabled(false);
        statusLabel.setText("Evaluating model...");
        statusLabel.setForeground(HEADER_COLOR);
        
        // Use SwingWorker again to avoid freezing the UI during evaluation
        SwingWorker<Double, Void> worker = new SwingWorker<Double, Void>() {
            private int trainSize;
            private int testSize;
            
            @Override
            protected Double doInBackground() throws Exception {
                // Create stratified training and test sets
                Map<String, List<DataInstance>> stratifiedData = createStratifiedSplit();
                
                // Train a new classifier on the training data
                NaiveBayes evaluationClassifier = new NaiveBayes();
                evaluationClassifier.train(stratifiedData.get("training"));
                
                // Calculate accuracy on the test set
                double accuracy = evaluationClassifier.evaluate(stratifiedData.get("testing"));
                
                // Store sizes for UI update
                this.trainSize = stratifiedData.get("training").size();
                this.testSize = stratifiedData.get("testing").size();
                
                return accuracy;
            }
            
            @Override
            protected void done() {
                try {
                    // Get the result
                    double accuracy = get();
                    
                    // Update UI with results
                    accuracyLabel.setText(String.format("Accuracy: %.2f%%", accuracy));
                    accuracyBar.setValue((int) accuracy);
                    trainSizeLabel.setText("Training Set Size: " + trainSize + " instances");
                    testSizeLabel.setText("Test Set Size: " + testSize + " instances");
                    
                    // Update status and re-enable button
                    statusLabel.setText("Evaluation completed successfully!");
                    statusLabel.setForeground(SUCCESS_COLOR);
                    
                    // Set color based on accuracy
                    if (accuracy >= 80) {
                        accuracyBar.setForeground(SUCCESS_COLOR);
                    } else if (accuracy >= 60) {
                        accuracyBar.setForeground(new Color(200, 160, 30)); // Yellow/orange
                    } else {
                        accuracyBar.setForeground(new Color(200, 50, 50)); // Red
                    }
                    
                } catch (Exception ex) {
                    statusLabel.setText("Error during evaluation: " + ex.getMessage());
                    statusLabel.setForeground(WARNING_COLOR);
                    ex.printStackTrace();
                } finally {
                    evaluateButton.setEnabled(true);
                }
            }
        };
        
        worker.execute();
    }
    
    /**
     * Creates stratified training and testing sets with a 75/25 split. (150 training, 50 testing out of 200 total)
     * Ensures that the class distribution in both sets closely matches the original dataset
     * @return A map containing "training" and "testing" datasets
     */
    private Map<String, List<DataInstance>> createStratifiedSplit() {
        // Separate the instances by label
        Map<String, List<DataInstance>> instancesByLabel = new HashMap<>();
        
        // Count the occurrences of each label
        for (DataInstance instance : dataList) {
            String label = instance.getLabel();
            if (!instancesByLabel.containsKey(label)) {
                instancesByLabel.put(label, new ArrayList<>());
            }
            instancesByLabel.get(label).add(instance);
        }
        
        // Create the training and test sets
        List<DataInstance> trainingSet = new ArrayList<>();
        List<DataInstance> testingSet = new ArrayList<>();
        
        // Use the current time as seed for different results each evaluation to avoid any bias
        Random random = new Random(System.currentTimeMillis());
        
        // For each label randomly assign 75% to training and 25% to testing
        for (Map.Entry<String, List<DataInstance>> entry : instancesByLabel.entrySet()) {
            List<DataInstance> instances = entry.getValue();
            
            // Shuffle the instances with this label
            Collections.shuffle(instances, random);
            
            // Calculate split point (75% for training)
            int trainCount = (int) Math.ceil(instances.size() * 0.75);
            
            // Add to training and testing sets
            for (int i = 0; i < instances.size(); i++) {
                if (i < trainCount) {
                    trainingSet.add(instances.get(i));
                } else {
                    testingSet.add(instances.get(i));
                }
            }
        }
        
        // Shuffle both sets to avoid any ordering bias
        Collections.shuffle(trainingSet, random);
        Collections.shuffle(testingSet, random);
        
        // Return the stratified sets
        Map<String, List<DataInstance>> result = new HashMap<>();
        result.put("training", trainingSet);
        result.put("testing", testingSet);
        
        return result;
    }
} 