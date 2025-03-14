/*
 * PersonGUI: A class that extends the JFrame class and is used to display a simple GUI to the user.
 *            The user can add new persons to an array list
 * Author: Ryan Pitman
 * Date: 13-03-2024
 */

package com.week7.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PersonGUI extends JFrame {
    private JTextField txtFirstName;
    private JTextField txtSurname;
    private JTextField txtCity;
    private JButton buttonSave;
    private JButton buttonShowAll;
    private JButton buttonDelete;
    private JTextArea textArea;
    
    // ArrayList to store Person objects
    private ArrayList<Person> persons;

    public PersonGUI() {
        super("Person Entry GUI");

        // Instantiate the ArrayList
        persons = new ArrayList<>();

        // Create labels and text fields for first name, surname, and city.
        JLabel lblFirstName = new JLabel("First Name:");
        JLabel lblSurname = new JLabel("Surname:");
        JLabel lblCity = new JLabel("City:");
        txtFirstName = new JTextField(15);
        txtSurname = new JTextField(15);
        txtCity = new JTextField(15);

        // Create buttons
        buttonSave = new JButton("Save");
        buttonShowAll = new JButton("Show All");
        buttonDelete = new JButton("Delete all");
        

        // Create a text area to display all Person objects
        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Layout for input fields using a grid
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.add(lblFirstName);
        inputPanel.add(txtFirstName);
        inputPanel.add(lblSurname);
        inputPanel.add(txtSurname);
        inputPanel.add(lblCity);
        inputPanel.add(txtCity);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonSave);
        buttonPanel.add(buttonShowAll);
        buttonPanel.add(buttonDelete);

        // Add panels to the frame
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Add action listener for the Save button.
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePerson();
            }
        });

        // Add action listener for the Show All button.
        buttonShowAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllPersons();
            }
        });

        // Add action listener for the Delete button.
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllPersons();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to handle the Save button click.
    private void savePerson() {
        // Get the values entered
        String firstName = txtFirstName.getText();
        String surname = txtSurname.getText();
        String city = txtCity.getText();

        // Verify they are not empty
        if (firstName.isEmpty() || surname.isEmpty() || city.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a Person object with the values entered.
        Person newPerson = new Person(firstName, surname, city);
        
        // Add the new Person object to the ArrayList.
        persons.add(newPerson);

        // Display the new Person object in a popup (using its toString() method).
        JOptionPane.showMessageDialog(this, newPerson.toString(), "Person Saved", JOptionPane.INFORMATION_MESSAGE);

        // Clear the text fields after saving to prevent duplicate entries.
        txtFirstName.setText("");
        txtSurname.setText("");
        txtCity.setText("");
    }

    // Method to handle the Show All button click.
    private void showAllPersons() {
        // StingBuilder to store all Person objects
        StringBuilder sb = new StringBuilder();

        // Loop through the ArrayList and append each Person object to the StringBuilder.
        for (Person p : persons) {
            sb.append(p.toString()).append("\n");
        }

        // Set the result string to the text area.
        textArea.setText(sb.toString());
    }

    // Method to handle the Delete button click.
    private void deleteAllPersons() {
        // Clear the ArrayList and the text area.
        persons.clear();
        textArea.setText("");

        // Display a message to the user.
        JOptionPane.showMessageDialog(this, "All persons have been deleted.", "Persons Deleted", JOptionPane.INFORMATION_MESSAGE);
    }
}


