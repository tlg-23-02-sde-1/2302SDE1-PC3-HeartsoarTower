package com.tlg.controller;

import com.tlg.model.Item;
import com.tlg.model.Player;
import com.tlg.model.Room;
import com.tlg.model.Scene;
import com.tlg.view.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * AlwaysCommands are commands that are available to the player regardless of the location
 */
class AlwaysCommands {
    protected static Boolean alwaysAvailableCommands(String[] instruct, Player player, Scene scene, List<Room> rooms, DisplayEngine displayEngine, DisplayArt art, DisplayText text, DisplayInput inputter, MusicPlayer musicPlayer) {
//            Functions that we need REGARDLESS of what room we are in or our inventory state:
        if (instruct[0] == null && instruct[1] == null) {
            text.setDisplay("Invalid Command.");
            displayEngine.printScreen(art, text, inputter, rooms);
            return true;
        }
        if (instruct[0] != null) {
            if (instruct[0].equalsIgnoreCase("quit")) {
                quitGame();
            } else if (instruct[0].equalsIgnoreCase("help")) {
                help();
                displayEngine.printScreen(art, text, inputter, rooms);
                return true;
            } else if (instruct[0].equalsIgnoreCase("look")) {
                if (instruct[1] == null || instruct[1].equalsIgnoreCase("around")) {
                    System.out.println("You look around the room.");
                    lookAround(player);
                    return true;
                }
            } else if (instruct[0].equalsIgnoreCase("music")) {
                musicSettings(musicPlayer);
            }
        }
        if (instruct[1] != null) {
            if (instruct[1].equalsIgnoreCase("inventory")) {
                text.setDisplay("You have the following items in your inventory:" + player.getInventory());
                displayEngine.printScreen(art, text, inputter, rooms);
            }
        } else if (instruct[0].equalsIgnoreCase("look") && instruct[1].equalsIgnoreCase("sword")) {
            lookAtSword();
        }


//        else {
//            System.out.println("Invalid Command. Please try another way.");
//        }
        return false;
    }


    private static void help() {
//      Clear the console screen:
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println(

                "    ██████████▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█▀▀█████████\n" +
                        "    █████████`Welcome to Heartsoar Tower █░░▒▐████████\n" +
                        "    █████████                           ▐░█░▒░████████\n" +
                        "    ████████▌ You can use the following: ▒▌▒▒▒████████\n" +
                        "    ████████▌                           █▒█▒▒▒████████\n" +
                        "    █████████   go <direction>          █▒▀░▒▐████████\n" +
                        "    █████████⌐    look <item>           ▐█████████████\n" +
                        "    █████████▌      look around          █████████████\n" +
                        "    ██████████       check inventory     ▐████████████\n" +
                        "    ███████████       talk <npc>          ████████████\n" +
                        "    ███████████µ        use <item>        ▐███████████\n" +
                        "    ████████████         drop <item>       ███████████\n" +
                        "    ████████████▌          music            ██████████\n" +
                        "    █████████████            quit           ▐█████████\n" +
                        "    █████████████▌                           █████████\n" +
                        "    ██████████████                           └████████\n" +
                        "    ██████████▀▀▀▀▀MMMMMMMMMMMMMMMMMMMMMMM█ß▄ ████████\n" +
                        "    ████████'                            ▐▌▒░▌▐███████\n" +
                        "    ███████▌                             █▒▒▒█▐███████\n" +
                        "    ███████▌  Press enter to continue    █▒▒▒█▐███████\n" +
                        "    ████████                             ▐▄▄▄▀▐███████\n" +
                        "    ████████▄                             █   ████████\n" +
                        "    █████████▀∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞∞4▀4█████████\n");
//        Press enter to continue
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static void lookAtItem(String item, Player player) {
        Item foundItem = null;
        for (Item sceneItem : player.getLocation().getItems()) {
            if (sceneItem.getName().equalsIgnoreCase(item)) {
                foundItem = sceneItem;
                break;
            }
        }
        if (foundItem == null) {
            for (Item inventoryItem : player.getInventory()) {
                if (inventoryItem.getName().equalsIgnoreCase(item)) {
                    foundItem = inventoryItem;
                    break;
                }
            }
        }
        if (foundItem != null) {
            // TODO: Remove sword and replace with a more generic item
            if (foundItem.getName().equalsIgnoreCase("Sword")) {
                System.out.println("It is a shiny sword, adorned with intricate carvings.");
            } else {
                System.out.println(foundItem.getDescription());
            }
        } else {
            System.out.println("You don't see that item here.");
        }
    }

    private static void lookAtSword() {
        System.out.println("It is a shiny sword, adorned with intricate carvings.");
        System.out.println("What do you want to do next?");
    }

    private static void lookAround(Player player) {
        System.out.println("You look around the room.");
        System.out.println(player.getLocation().getDesc());
    }

    private static void musicSettings(MusicPlayer musicPlayer) {
        System.out.println("=== Music Settings ===");
        System.out.println("1. Play music");
        System.out.println("2. Stop music");
        System.out.println("3. Adjust volume");
        System.out.println("Enter the number of your choice:");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                musicPlayer.play("src/main/resources/Music/medievalrpg-music.wav");
                break;
            case 2:
                musicPlayer.stop();
                break;
            case 3:
                System.out.println("Enter the volume (1-100):");
                int volumeInput = scanner.nextInt();
                if (volumeInput >= 1 && volumeInput <= 100) {
                    float volume = volumeInput / 100f;
                    musicPlayer.setVolume(volume);
                    System.out.println("Volume adjusted to " + volumeInput);
                } else {
                    System.out.println("Invalid volume input. Please enter a value between 1 and 100.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static boolean quitGame() {
        System.out.println("Would you like to quit the game? Y/N");
        Scanner scanner = new Scanner(System.in);
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
            return true;
        }
        return true;
    }

}