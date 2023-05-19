package com.tlg.art;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TitleScreen {

    public static void displayTitleScreen() {
        String asciiArtFile = "src/main/resources/Ascii_art/TitleScreen.txt";

        String asciiArt = readAsciiArtFromFile(asciiArtFile);

        displayTitleScreen(asciiArt);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        clearScreen();
    }

    private static String readAsciiArtFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static void displayTitleScreen(String asciiArt) {
        System.out.print(asciiArt);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
