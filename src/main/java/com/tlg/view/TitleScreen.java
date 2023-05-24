package com.tlg.view;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class TitleScreen {

    private static String titleScreen;

    public static void displayTitleScreen() {
//        Two threads -- one to display the title screen, the other to wait for the user to press Enter.
        Thread thread1 = new Thread(() -> {
            displayTitleScreen(titleScreen);
//            Sleep for 5 seconds:
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
//            Cancel thread1's sleep if we press a key:
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            thread1.interrupt();
        });
//        thread1.start();
//        thread2.start();

//        System.out.println("\nPress Enter to continue...");
//        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();

        clearScreen();
    }

    private static void displayTitleScreen(String asciiArt) {
        System.out.print(asciiArt);
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
//        Clear the console screen:
//        try{
//            final String os = System.getProperty("os.name");
//            if (os.contains("Windows")) {
//                Runtime.getRuntime().exec("cls");
//            } else {
//                Runtime.getRuntime().exec("clear");
//            }
//
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }

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
