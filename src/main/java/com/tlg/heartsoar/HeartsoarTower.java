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
    }
    void basicInfo() {
        System.out.println("Welcome, Harmony, to the enchanted world of Terra Motus.");
        System.out.println("Story:");
        System.out.println("The King and Queen of Terra Motus are grief-stricken. An evil curse has sealed away their young Prince Timore in a tower at the edge of their kingdom.");
        System.out.println("The royalty have requested you, Harmony, to enter the tower and save their gentle son.");
        System.out.println("Armed with only your trusted sword and an amulet wrapped in a handkerchief given by the queen, you approach the ominous tower, unsure of what lies within.");
        System.out.println("Objective:");
        System.out.println("Your mission is to navigate through the tower, overcoming challenges along the way.");
        System.out.println("To win, you must free Prince Timore from the curse by reaching the top of the tower.");

        System.out.println("Are you ready to begin your journey, Harmony? The fate of Prince Timore rests in your hands!");

    }

    void quitGame() {
        System.out.println("Would you like to quit the game? Y/N");
        String userInput = scanner.nextLine();
        while (!"Y".equalsIgnoreCase(userInput) && !"Yes".equalsIgnoreCase(userInput) &&
                !"N".equalsIgnoreCase(userInput) && !"No".equalsIgnoreCase(userInput)) {
            System.out.println("Invalid input. Please enter Y/Yes to confirm quitting, or N/No to continue playing.");
            userInput = scanner.nextLine();
        }
        if ("Y".equalsIgnoreCase(userInput) || "Yes".equalsIgnoreCase(userInput)) {
            System.out.println("Quitting the game. Goodbye!");
            System.exit(0);
        } else {
            System.out.println("Returning to the start..");
            newGame();
        }
    }



}

