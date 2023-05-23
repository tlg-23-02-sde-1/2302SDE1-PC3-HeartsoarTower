package com.tlg.heartsoar;

import java.util.List;

class SceneBuilder {
    private String room;
    private String description;
    private List<String> items;
    private List<String> monsters;

    SceneBuilder(String room, String description, List<String> items, List<String> monsters) {
        this.description = description;
        this.items = items;
        this.monsters = monsters;
    }

    public String getRoom() {
        return room;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getItems() {
        return items;
    }

    public List<String> getMonsters() {
        return monsters;
    }
}