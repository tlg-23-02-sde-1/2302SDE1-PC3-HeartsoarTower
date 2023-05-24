package com.tlg.model;

import java.util.List;
import java.util.Random;

public class Monster {
    private String name;
    private List<String> failures; // Single words that result in failure
    private List<String[]> successes; // Noun + verb that result in success
    private List<String> description; // Description of the monster after each success action
    private String sceneFailed; // Scene to go to if the player fails to kill the monster
    private List<String> dialogue;



    public Monster(String name, List<String[]> successes, List<String> failures, List<String> description, String sceneFailed, List<String> dialogue) {
        this.name = name;
        this.successes = successes;
        this.failures = failures;
        this.description = description;
        this.sceneFailed = sceneFailed;
        this.dialogue = dialogue;
    }


    public String getName() {
        return name;
    }



    public String talk() {
        Random random = new Random();
        int dialogueIndex = random.nextInt(dialogue.size());  // Randomly select a dialogue
        return dialogue.get(dialogueIndex);  // Return the selected dialogue
    }

    public List<String> getFailures() {
        return failures;
    }

    public List<String[]> getSuccesses() {
        return successes;
    }
    public void progressDescription(){
        System.out.println(description.get(0));
        description.remove(0);
    }
    public void getSceneFailed() {
        System.out.println(sceneFailed);
    }
}
