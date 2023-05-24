package com.tlg.view;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class TitleScreen {

    private static String titleScreen;


    public static void displayTitleScreen() {
        displayTitleScreen(titleScreen);

        System.out.println("\nPress Enter to continue:");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        clearScreen();
    }

    private static void displayTitleScreen(String asciiArt) {
       System.out.print(asciiArt);
   }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    private static String readResource(String path) throws IOException {
        try (InputStream is = TitleScreen.class.getResourceAsStream(path)) {
            if (is == null) {
                throw new FileNotFoundException("Resource not found: " + path);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    static {
        try {
            titleScreen = readResource("/Ascii_art/TitleScreen.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
