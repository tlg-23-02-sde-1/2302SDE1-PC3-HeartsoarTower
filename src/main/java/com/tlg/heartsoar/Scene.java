package com.tlg.heartsoar;

class Scene {
    private String name;
    private String description;
    private String[] items;
    private String[] monsters;


    Scene(String name, String description, String[] items, String[] monsters) {
        this.name = name;
        this.description = description;
        this.items = items;
        this.monsters = monsters;
    }

    String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    String[] getItems() {
        return items;
    }

    String[] getMonsters() {
        return monsters;
    }
}
