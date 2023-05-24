package com.tlg.controller;

import com.tlg.model.*;
import com.tlg.view.TitleScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import static com.tlg.controller.AlwaysCommands.alwaysAvailableCommands;
import static com.tlg.controller.CombatEngine.combatCommands;
import static com.tlg.controller.NewGame.newGame;
import static com.tlg.controller.SpecificCommands.specificCommands;

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
    private boolean isRunning;
    private List<Scene> scenes = factory.getScenes();

    HeartsoarTower() throws IOException {
        this.player = new Player(rooms);
        this.isRunning = true;
    }

    void gameLoop() {

        TitleScreen.displayTitleScreen();
        newGame();
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            grabScene();
            System.out.println("Enter a command:");
            String input = scanner.nextLine();
            String [] instruct = textParser.validCombo(input);
            Boolean actionTaken = false;
            if (scene.getAllSceneMonsters().size() != 0) actionTaken = combatCommands(instruct, player, scene);
            if (!actionTaken) actionTaken = alwaysAvailableCommands(instruct, player, scene, rooms);
            if (!actionTaken) actionTaken = alwaysAvailableCommands(instruct, player, scene, rooms);
            if (!actionTaken) actionTaken = specificCommands(instruct, player, scene);
            if (!actionTaken) System.out.println("Invalid Command.");
        }
    }
    private void grabScene() {
        for (Scene scene : scenes) {
            if (scene.getRoom().equals(player.getLocation())) {
                this.scene = scene;
            }
        }
//        Print the description based on if the monster is present (0), if an item is present(1), or if complete(3)
        if (scene.getAllSceneMonsters().size() != 0) {
            System.out.println(scene.getDescription(0));
        }
        else if (scene.getSceneItems().size() != 0) {
            System.out.println(scene.getDescription(1));
        }
        else {
            System.out.println(scene.getDescription(2));
        }

    }
    public static void main(String[] args) throws IOException {
        HeartsoarTower game = new HeartsoarTower();
        game.gameLoop();
    }
}

