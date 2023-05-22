package com.tlg.heartsoar;

class Map {

//    Need instructions of game on back:
//    Need display

//    Help features

    private final String instructions =  "Heartsoar allows for various common commands to be entered to allow you to control Harmony." +
            "\nAt any time you can type 'help' to display a menu" +
            "\nYou can quit the game just by typing 'quit'" +
            "\nYou can 'look' at items in the room or in your inventory" +
            "\nYou can 'go' in any direction that is available to you" +
            "\nYou can 'take' items that are in the room" +
            "\nYou can 'drop' items that are in your inventory" +
            "\nYou can 'use' items that are in your inventory" +
            "\nYou can 'attack' monsters that are in the room, or even 'talk' to them" +
            "\nThere are many more commands that you will discover as you play.  Read the dialogue closely to acquire hints!";

    public String getInstructions() {
        return instructions;
    }
}
