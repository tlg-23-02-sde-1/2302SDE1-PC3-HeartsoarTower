package com.tlg.heartsoar;

import java.util.List;

import java.util.Scanner;

class HeartsoarTower {
//    TODO: Remove nouns and verbs from here once JSON implemented
    List<String> NOUNS;
    List<String> VERBS;
    private TextParser textParser = new TextParser(VERBS, NOUNS);

//    TODO: Place in proper location once game loop established
    // Take input from the user via the console:
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] instruct = textParser.validCombo(input);

    void newGame() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;

        System.out.println("Welcome to Heartsoar Tower!");
        System.out.println("Please enter 'New Game' to start a new game:");
        userInput = inputScanner.nextLine();
        if ("New Game".equalsIgnoreCase(userInput)) {
            System.out.println("Starting a new game...");
            //playGame()
        } else {
            System.out.println("Invalid. Please enter 'New Game' to start the game.");
        }

        inputScanner.close();
    }
}

