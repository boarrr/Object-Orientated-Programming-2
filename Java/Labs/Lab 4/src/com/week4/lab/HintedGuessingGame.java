/*
 * HintedGuessingGame: A guessing game child that provides hints to the user on their guess based off the distance of their guess
 * Author: Ryan Pitman
 * Date: 20/02/2025
 */

package com.week4.lab;

public class HintedGuessingGame extends GuessingGame {
    
    // Constructor
    public HintedGuessingGame() {
        super();
    }

    // Override the intro text to the game
    @Override
    public void displayIntro() {
        System.out.println("Welcome to the Hinted Number Guessing Game!");
        System.out.println("I will provide you with a hint on how close your guess is to the number I am thinking of.");
    }

    // Override to provide a hint of the difference instead of just high/low feedback
    @Override
    public void guessHighLow() {
        int difference = Math.abs(getNumToGuess() - getUserGuess());

        // If the difference is 0, the guess is correct, so no need to provide a hint
        if (difference == 0) {
            return;
        }

        System.out.println("Your guess is off by " + difference);
    }
}

