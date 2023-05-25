package com.tlg.view;

import com.tlg.model.Monster;

import java.util.ArrayList;
import java.util.List;

public class DisplayArt extends Display {
    private final static int MAX_LINES = 10;
    private String display;

    public DisplayArt() {
        super(MAX_LINES);
    }

    public void displayMonster(Monster monster) {
        System.out.println(MAX_LINES);
        setDisplay(monster.getArt());
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}

