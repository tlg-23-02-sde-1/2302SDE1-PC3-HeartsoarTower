package com.tlg.controller;

import com.tlg.model.*;
import com.tlg.view.TitleScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import static com.tlg.controller.AlwaysCommands.alwaysAvailableCommands;
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
            Boolean actionTaken = alwaysAvailableCommands(instruct, player, scene, rooms);
            if (!actionTaken) specificCommands(instruct, player, scene);
        }
    }
    private void grabScene() {
        for (Scene scene : scenes) {
            if (scene.getRoom().equals(player.getLocation())) {
                this.scene = scene;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        HeartsoarTower game = new HeartsoarTower();
        game.gameLoop();
    }
}

