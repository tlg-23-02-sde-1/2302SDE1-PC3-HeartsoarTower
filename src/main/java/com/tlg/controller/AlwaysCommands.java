package com.tlg.controller;

import com.tlg.model.Item;
import com.tlg.model.Player;
import com.tlg.model.Room;
import com.tlg.model.Scene;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


/**
 * AlwaysCommands are commands that are available to the player regardless of the location
 */
class AlwaysCommands {
    protected static Boolean alwaysAvailableCommands(String[] instruct, Player player, Scene scene, List<Room> rooms){
//            Functions that we need REGARDLESS of what room we are in or our inventory state:
        if (instruct == null) {
            System.out.println("Invalid Command.");
            return false;
        }
        else if (instruct[0].equalsIgnoreCase("quit")) {
            quitGame();
        }
        else if (instruct[0].equalsIgnoreCase("help")) {
            help();
            return true;
        }
        else if (instruct[1].equalsIgnoreCase("inventory")) {
            System.out.println("You have the following items in your inventory:");
            System.out.println(player.getInventory());
        }
        else if (instruct[0].equalsIgnoreCase("look") && instruct[1].equalsIgnoreCase("sword")) {
            lookAtSword();
        }
        else if (instruct[0].equalsIgnoreCase("look")) {
            if (instruct[1] == null || instruct[1].equalsIgnoreCase("around")){
                System.out.println("You look around the room.");
                lookAround(player);
            }
        }
        else if (instruct[0].equalsIgnoreCase("go")) {
            HashMap<String, String> acceptableDirections = player.getLocation().getNeighborRooms();
            if (!acceptableDirections.containsKey(instruct[1])) {
                System.out.println("You cannot go that way.");
                return true;
            }
//                TODO Step2: Ensure the monster will allow you to flee
            player.setPrevLocation(player.getLocation());
            Room nextRoom;
            String direction = instruct[1];
            String roomDir = player.getLocation().getNeighborRooms().get(direction);
//            TODO: Rework Rooms and Scene to transfer descriptions to scene
            for (Room room : rooms) {
                if (room.getName().equals(roomDir)) {
                    nextRoom = room;
                    player.setLocation(nextRoom);
                    System.out.println("You have entered the " + player.getLocation().getName() + ".");
                    System.out.println(scene.getDescription());
                    return true;
                }
            }
        }
//        else {
//            System.out.println("Invalid Command. Please try another way.");
//        }
        return false;
    }


    private static void help() {
        System.out.println("You can use the following commands:");
        System.out.println("go <direction>");
        System.out.println("look <item>");
        System.out.println("check inventory");
        System.out.println("quit");
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