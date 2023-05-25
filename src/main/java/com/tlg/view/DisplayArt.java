package com.tlg.view;

import com.tlg.model.Monster;

public class DisplayArt extends Display {
    private final static int MAX_LINES = 30;
    private String display;

    public DisplayArt() {
        super(MAX_LINES);
    }

    public void displayMonster(Monster monster) {
        System.out.println(MAX_LINES);
        setDisplay(monster.getArt());
    }

    public void setDisplay(String display) {
        this.display = super.trimDisplay(display, MAX_LINES);
    }

    public String getDisplay() {
        return display;
    }

}

