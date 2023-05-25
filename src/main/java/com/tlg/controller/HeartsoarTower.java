package com.tlg.controller;

import com.tlg.model.*;
import com.tlg.view.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import static com.tlg.controller.AlwaysCommands.alwaysAvailableCommands;
import static com.tlg.controller.CombatEngine.combatCommands;
import static com.tlg.controller.MoveCommand.moveCommands;
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
    private DisplayEngine displayEngine = new DisplayEngine();
    private DisplayArt art = new DisplayArt();
    private DisplayInput inputter = new DisplayInput();
    private DisplayText text = new DisplayText();
    private MusicPlayer musicPlayer;


    HeartsoarTower() throws IOException {
        this.player = new Player(rooms);
        this.isRunning = true;
        this.musicPlayer = new MusicPlayer();
    }

    void gameLoop() {

        TitleScreen.displayTitleScreen();
        musicPlayer.setFilePath("src/main/resources/Music/medievalrpg-music.wav");
        musicPlayer.play();
        newGame();
        Scanner scanner = new Scanner(System.in);
        boolean justEntered = true;
        while (isRunning) {
            if (justEntered) grabScene();
            justEntered = false;
            String input = scanner.nextLine();
            String[] instruct = textParser.validCombo(input);
            Boolean actionTaken = false;
            if (scene.getAllSceneMonsters().size() != 0)
                actionTaken = combatCommands(instruct, player, scene, art, text, inputter, displayEngine, rooms);
            if (!actionTaken) actionTaken = alwaysAvailableCommands(instruct, player, scene, rooms);
            if (!actionTaken) actionTaken = specificCommands(instruct, player, scene);
            if (!actionTaken) {
                actionTaken = moveCommands(instruct, player, scene, rooms, displayEngine);
                if (actionTaken) {
                    justEntered = true;
                    displayEngine.printScreen(art, text, inputter, rooms);
                }
            }
            if (!actionTaken) {
                text.setDisplay("I do not know that command.  Please try again:    ");
                displayEngine.printScreen(art, text, inputter, rooms);
            }
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
            String monsterPicture = scene.getAllSceneMonsters().get(0).getArt();
            art.setDisplay(monsterPicture);
            text.setDisplay(scene.getDescription(0));
            inputter.setDisplay("Enter a command:    ");
            displayEngine.printScreen(art, text, inputter, rooms);
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

