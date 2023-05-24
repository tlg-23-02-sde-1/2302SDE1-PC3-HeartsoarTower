package com.tlg.model;

public class Item {
    private String name;
    private String description;
    private String onUse;
    private boolean isStorable;


    public Item(String name, String description, boolean isStorable, String onUse) {
        this.name = name;
        this.description = description;
        this.isStorable = isStorable;
        this.onUse = onUse;
    }

    public String use(Room room) {
        return onUse;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStorable() {
        return isStorable;
    }
}
