package com.tlg.heartsoar;

class Item {
    private String name;
    private String description;
    private boolean isStorable;
    public Item(String name, String description, boolean isStorable) {
        this.name = name;
        this.description = description;
        this.isStorable = isStorable;
    }
}
