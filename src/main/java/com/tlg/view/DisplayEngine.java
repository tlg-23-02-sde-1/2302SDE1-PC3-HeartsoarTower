package com.tlg.view;

public class DisplayEngine {
    // Split the screen in thirds:
//    1. Top third is the art display. ( or map )
//    2. Middle third is the text display.
//    3. Bottom third is the input display.
    private DisplayArt artDisplay;
    private DisplayText textDisplay;
    private DisplayInput displayInput;
    private static String lineBreak = "------------------------------------------------------------------------------------------------------------------------";



    public static void printScreen(DisplayArt artDisplay, DisplayText textDisplay, DisplayInput inputDisplay) {
//        Clear the screen.
        System.out.print("\033[H\033[2J");
        System.out.println(artDisplay.getDisplay());
        System.out.println(lineBreak);
        System.out.println(textDisplay.getDisplay());
        System.out.println(lineBreak);
        System.out.println(inputDisplay.getDisplay());
    }






}