package com.tlg.controller;

import com.tlg.model.Monster;
import com.tlg.model.Player;
import com.tlg.model.Scene;

import java.util.List;

class CombatEngine {

    public static boolean combatCommands(String[] instruct, Player player, Scene scene) {
        boolean actionTaken = false;
//        Verify there's even a monster in the room:
        if (scene.getSceneMonsters(0) == null) return actionTaken;
//        Every monster has some acceptable commands for defeating:
        Monster monster = scene.getSceneMonsters(0);
        List<String[]> successes = monster.getSuccesses();
//        Every monster has some commands for losing:
        List<String> failures = monster.getFailures();
//        To defeat the monster we have to do every command IN ORDER:
        String successVerb = successes.get(0)[0];
        String successNoun = successes.get(0)[1];

        if (successVerb.equalsIgnoreCase(instruct[0]) && successNoun.equalsIgnoreCase(instruct[1])){
            successes.remove(0);
            actionTaken = true;
            if (successes.size() == 0) {
                scene.defeatMonster(scene.getSceneMonsters(0));
            }
            monster.progressDescription();
        }

        else if(failures.contains(instruct[0])){
            actionTaken = true;
            monster.getSceneFailed();
//            TODO: RETURN TO SAVE POINT
        }
        return actionTaken;
    }
}