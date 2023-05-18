package com.tlg.heartsoar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.tlg.language.TextParser;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

class HeartsoarTower {
    Factory factory = new Factory();
    List<Room> rooms = factory.getRooms();
    Collection<String[]> VERBS = factory.getVerbs();
    Collection<String[]> NOUNS = factory.getNouns();

    HeartsoarTower() throws IOException {

    }


//
////    TODO: Place in proper location once game loop established
//    // Take input from the user via the console:
//    Scanner scanner = new Scanner(System.in);
//    String input = scanner.nextLine();
//    String[] instruct = textParser.validCombo(input);

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

    public static void main(String[] args) throws IOException {
        HeartsoarTower heartsoarTower = new HeartsoarTower();
        heartsoarTower.newGame();
    }
}

