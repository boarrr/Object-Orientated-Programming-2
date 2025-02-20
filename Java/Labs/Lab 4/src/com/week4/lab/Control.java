/*
 * Control: The main controller class of the guessing game, facilitates main menu input
 * Author: Ryan Pitman
 * Date: 20/02/2025
 */

package com.week4.lab;

import java.util.Scanner;

public class Control {
    // Attributes
    private static int menuChoice;
    private static Scanner scanner = new Scanner(System.in);

    // Main method
    public static void main(String[] args) {
        // Display menu to user
        displayMenu();

        // Take user input
        menuChoice = scanner.nextInt();

        // Loop until user chooses to exit
        while (menuChoice != 3) {
            switch (menuChoice) {
                case 1:
                    GuessingGame guessingGame = new GuessingGame();
                    guessingGame.play();
                    break;
                case 2:
                    HintedGuessingGame hintedGuessingGame = new HintedGuessingGame();
                    hintedGuessingGame.play();
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }

            // Display menu to user
            displayMenu();

            // Take user input
            menuChoice = scanner.nextInt();
        }

        System.out.println("Goodbye!");
        scanner.close();
    }    

    private static void displayMenu() {
        System.out.println("\nWelcome to the Number Guessing Game!");
        System.out.println("1. Play Guessing Game");
        System.out.println("2. Play Hinted Guessing Game");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
}
