/*
 * Screen: A class that extends the JFrame class and is used to display a simple GUI to the user.
 * The GUI will contain a text area for the user to enter text, two buttons, and a mouse event panel
 * Layout is border layout style, following this pattern:
 *  - North: Text field panel
 *  - Center: Button panel
 *  - South: Mouse event panel
 * Author: Ryan Pitman
 * Date: 27-02-2025
 */

package com.week5.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Screen extends JFrame {
    // Attributes for the GUI components
    private JTextField textField;
    private JButton button1, button2;
    private JPanel mousePanel;
    
    // Constructor
    public Screen() {
        // Setup the screen, utilising border layout
        setTitle("Lab 5");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Add the GUI components to the screen
        add(createTextFieldPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
        add(createMousePanel(), BorderLayout.SOUTH);
        
        // Make the screen visible
        setVisible(true);
    }
    
    // Create the text field panel with a label and text field
    private JPanel createTextFieldPanel() {
        JPanel panel = new JPanel();
        
        // Create the label and text field
        JLabel label = new JLabel("Enter text:");
        textField = new JTextField(20);
        textField.setToolTipText("Enter your name here");

        // Event listener for the text field
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Text entered: " + textField.getText());
            }
        });

        // Add the label and text field to the panel
        panel.add(label);
        panel.add(textField);

        // Return the panel
        return panel;
    }
    
    // Create the button panel with two buttons
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        // Create the buttons and set the tooltips
        button1 = new JButton("Click Me");
        button1.setToolTipText("Click this button");
        button2 = new JButton("Submit");
        button2.setToolTipText("Submit your text");
        
        // Event listener for button1
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button 1 clicked");
            }
        });

        // Event listener for button2
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Button 2 clicked");
            }
        });

        // Add the buttons to the panel
        panel.add(button1);
        panel.add(button2);

        // Return the button panel
        return panel;
    }
    
    // Create the mouse event panel
    private JPanel createMousePanel() {
        mousePanel = new JPanel();

        // Set the size and background color of the panel, and add a label
        mousePanel.setPreferredSize(new Dimension(200, 100));
        mousePanel.setBackground(Color.RED);
        JLabel mouseLabel = new JLabel("Mouse Event Panel");
        mousePanel.add(mouseLabel);
        
        mousePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel containerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        containerPanel.add(mousePanel);
        
        // Event listeners for the mouse panel
        mousePanel.addMouseListener(new MouseAdapter() {

            // If mouse enters the panel, show a message dialog
            @Override
            public void mouseEntered(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Mouse entered the panel");
            }

            // If mouse exits the panel, show a message dialog
            @Override
            public void mouseExited(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Mouse left the panel");
            }
            
            // If mouse is clicked on panel
            @Override
            public void mouseClicked(MouseEvent e) {
                // Check if left or right mouse button was clicked
                if (SwingUtilities.isLeftMouseButton(e)) {
                    JOptionPane.showMessageDialog(null, "Left click on the panel");
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    JOptionPane.showMessageDialog(null, "Right click on the panel");
                }
            }
        });

        // Return the container, which contains the mouse panel
        return containerPanel;
    }
}
