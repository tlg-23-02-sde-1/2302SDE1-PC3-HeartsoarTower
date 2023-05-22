package com.tlg.heartsoar;

import java.util.HashMap;
import java.util.List;

class Room {
    private final String name;
    private String[] desc = new String[3];
//    desc[0] = Monster Present Item Present
//    desc[1] = Monster not Present Item Present
//    desc[2] = Complete
    private List<String> nouns;
    private String monster;
    private final HashMap<String, String> neighborRooms;
    private boolean isDiscovered;

    public Room(String name, String[] desc, List<String> nouns, String monster, HashMap<String, String> neighborRooms) {
        this.name = name;
        this.desc = desc;
        this.nouns = nouns;
        this.monster = monster;
        this.neighborRooms = neighborRooms;
        this.isDiscovered = false;
    }

    public String getName() {
        return name;
    }

    public String[] getDesc() {
        return desc;
    }

    public List<String> getNouns() {
        return nouns;
    }

    public String getMonster() {
        return monster;
    }

    public HashMap<String, String> getNeighborRooms() {
        return neighborRooms;
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setNouns(List<String> nouns) {
        this.nouns = nouns;
    }

    public void setMonster(String monster) {
        this.monster = monster;
    }

    public void setDiscovered(boolean discovered) {
        isDiscovered = discovered;
    }

    public Item[] getItems() {
        return null;
    }
}

