/*
 * GuessingGame: A class that represents a simple guessing game, the user will guess until they get the number right
 *               The users guess count will be tracked and displayed at the end of the game
 *               The user will be displayed if they are high or low on their guess
 * Author: Ryan Pitman
 * Date: 20/02/2025
 */

package com.week4.lab;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    // Attributes
    private int numToGuess;
    private int userGuess;
    private int numGuesses = 0;
    private Scanner scanner = new Scanner(System.in);

    // Constructor
    public GuessingGame() {
        // Random number between 1 and 100, +1 to make it inclusive of 100
        Random rand = new Random();
        numToGuess = rand.nextInt(101); 
    }
    
    // Methods
    public void play() {
        displayIntro();

        do {
            System.out.print("Guess: ");
            userGuess = scanner.nextInt();
            numGuesses++;

            // Check if the guess is too high or too low
            guessHighLow();

        } while (!checkGuess());

        System.out.println("Congratulations! You guessed the number in " + numGuesses + " guesses!");
    }

    // Compare user guess with the number to guess and return true if they are the same
    public boolean checkGuess() {
        if (userGuess == numToGuess) {
            return true;
        } else {
            return false;
        }
    }

    // Print if the user guess is too high or too low
    public void guessHighLow() {
        if (userGuess < numToGuess) {
            System.out.println("Your guess is too low!");
        } else if (userGuess > numToGuess) {
            System.out.println("Your guess is too high!");
        }
    }

    // Display intro message
    public void displayIntro() {
        System.out.println("Welcome to the number Guessing Game!");
        System.out.println("Please guess a number between 1 and 100!\n");
    }

    // Getters
    public int getNumGuesses() {
        return numGuesses;
    }

    public int getNumToGuess() {
        return numToGuess;
    }

    public int getUserGuess() {
        return userGuess;
    }
}
