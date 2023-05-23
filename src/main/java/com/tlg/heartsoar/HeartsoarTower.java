package com.tlg.heartsoar;

import com.tlg.art.TitleScreen;
import com.tlg.language.TextParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

class HeartsoarTower {
    private Factory factory = new Factory();
    private List<Room> rooms = factory.getRooms();
    private List<Item> items = factory.getItems();
    private List<Monster> monsters = factory.getMonsters();
    private TreeMap<String, ArrayList<String>> VERBS = factory.getVerbs();
    private TreeMap<String, ArrayList<String>> NOUNS = factory.getNouns();
    private TextParser textParser = new TextParser(VERBS, NOUNS);
    private Player player;
    private Scene scene;
    private List<Scene> scenes = factory.getScenes();


    HeartsoarTower() throws IOException {
        this.player = new Player(rooms);
    }

    void gameLoop() {
        boolean isRunning = true;
        TitleScreen.displayTitleScreen();
        newGame();
        basicInfo();

        while (isRunning) {
            grabScene();
            System.out.println("Enter a command:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String [] instruct = textParser.validCombo(input);
            Boolean actionTaken = alwaysAvailableCommands(instruct);
            if (!actionTaken) specificCommands(instruct);
        }
    }

    private void grabScene() {
        for (Scene scene : scenes) {
            if (scene.getRoom().equals(player.getLocation())) {
                this.scene = scene;
            }
        }
    }

    private Boolean alwaysAvailableCommands(String[] instruct) {
//            Functions that we need REGARDLESS of what room we are in or our inventory state:
        if (instruct[0] == null && instruct[1] == null) {
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
        else if (instruct[0].equalsIgnoreCase("inventory")) {
            System.out.println(player.getInventory());
            return true;
        }
        else if (instruct[0].equals("look") && instruct.length > 1) {
            lookAtItem(instruct[1]);
            return true;
        }
        else if (instruct[0].equals("go") && instruct.length > 1) {
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
            for (Room room : rooms) {
                if (room.getName().equals(roomDir)) {
                    nextRoom = room;
                    player.setLocation(nextRoom);
                    System.out.println("You have entered the " + player.getLocation().getName() + ".");
                    System.out.println(player.getLocation().getDesc());
                }
            }
            return true;
        }
        return false;
    }

    private void specificCommands(String[] instruct) {
//        Get item:
        if (instruct[0].equalsIgnoreCase("get")) {
//            Check to see if the item is in the room:
            Boolean itemFound = false;
            for (Item item : scene.getSceneItems()) {
                if (item.getName().equalsIgnoreCase(instruct[1])) {
//                    Add to our inventory:
                    player.addItemToInventory(item);
//                    Remove from the scene:
                    scene.removeItem(item);
                    itemFound = true;
                }
                if (!itemFound) System.out.println("I cannot get that item.");
            }
        }
    }

    private void help() {
        System.out.println("You can use the following commands:");
        System.out.println("go <direction>");
        System.out.println("look <item>");
        System.out.println("inventory");
        System.out.println("quit");
    }

    private void lookAtItem(String item) {
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
            System.out.println(foundItem.getDescription());
        } else {
            System.out.println("You don't see that item here.");
        }
    }

    void newGame() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;

        System.out.println("Welcome to Heartsoar Tower!");
        while (true) {
            System.out.println("Please enter 'New Game' to start a new game:");
            userInput = inputScanner.nextLine();
            if ("New Game".equalsIgnoreCase(userInput)) {
                System.out.println("Starting a new game...");
                break;
            } else {
                System.out.println("Invalid. Please enter 'New Game' to start the game.");
            }
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

    boolean quitGame() {
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
            return false;
        } else {
            System.out.println("Returning to the start..");
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        HeartsoarTower game = new HeartsoarTower();
        game.gameLoop();
    }
}

