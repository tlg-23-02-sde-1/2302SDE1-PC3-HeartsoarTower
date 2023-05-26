package com.tlg.controller;

import com.tlg.model.*;
import com.tlg.view.DisplayArt;
import com.tlg.view.DisplayEngine;
import com.tlg.view.DisplayInput;
import com.tlg.view.DisplayText;

import java.util.List;


/**
 * Handles commands that need awareness of model state.
 */
class SpecificCommands {
    protected static boolean specificCommands(String[] instruct, Player player, Scene scene, DisplayEngine displayEngine, DisplayArt art, DisplayText text, DisplayInput input, List<Room> rooms) {
//        Get item:
        if (instruct[0].equalsIgnoreCase("get")) {
//            Check to see if the item is in the room:
            if (scene.getSceneItems().size() == 0) {
                text.setDisplay("There is nothing to get.");
                displayEngine.printScreen(art, text, input, rooms);
                return true;
            }
            if (instruct[1] == null) {
                text.setDisplay("I could not pick that up.");
                displayEngine.printScreen(art, text, input, rooms);
                return true;
            }
            for (Item item : scene.getSceneItems()) {
                if (item.getName().equalsIgnoreCase(instruct[1])) {
//                    Add to our inventory:
                    player.addItemToInventory(item);
                    art.setDisplay(item.getArt());
                    text.setDisplay("You picked up the " + item.getName() + " and added to your inventory.");
                    displayEngine.printScreen(art, text, input, rooms);
//                    Remove from the scene:
                    scene.removeItem(item);
                    return true;

                }
            }
            text.setDisplay("There is no " + instruct[1] + " here.");
            displayEngine.printScreen(art, text, input, rooms);
            return true;

        } else if (instruct[0].equalsIgnoreCase("drop")) {
//            Check to see if the item is in the inventory:
            if (player.getInventory().size() == 0) {
                text.setDisplay("You have nothing to drop.");
                displayEngine.printScreen(art, text, input, rooms);
                return true;
            }
            for (Item item : player.getInventory()) {
                if (item.getName().equalsIgnoreCase(instruct[1])) {
                    player.removeItemFromInventory(item);
                    scene.addItem(item);
                    text.setDisplay("You dropped the " + item.getName() + ".");
                    displayEngine.printScreen(art, text, input, rooms);
                    return true;
                }
            }
        }
        else if (instruct[0].equalsIgnoreCase("talk")) {
            // iterate over the monsters in the scene
            for (Monster monster : scene.getAllSceneMonsters()) {
                // if the monster name matches the second word in the command,
                if (monster.getName().equalsIgnoreCase(instruct[1])) {
                    // print the dialogue returned by the monster's talk() method
                    text.setDisplay(monster.getName() + ": " + monster.talk());
                    displayEngine.printScreen(art, text, input, rooms);
                    return true;
                }
            }
            text.setDisplay("There is no monster by that name here.");
            displayEngine.printScreen(art, text, input, rooms);
            return true;
        }
        return false;
    }

}