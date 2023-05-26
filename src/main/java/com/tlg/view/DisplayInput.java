package com.tlg.view;

import com.tlg.model.Player;

public class DisplayInput extends Display{
    private final static int MAX_LINES = 1;
    private String display;
    Player player;

    public DisplayInput(Player player) {
        super(MAX_LINES);
        this.player = player;
    }

    public String getDisplay() {
        setDisplay();
        return display;
    }

    public void setDisplay() {
//        We will be displaying PlayerName: Current Location.  List of items.
        String location = player.getLocation().getName();
        String inventory = player.getInventory().stream().map(e -> e.getName()).reduce("", (a, b) -> a + " " + b);
        this.display = "Command  >                    " + player.getName() + ".\tLocation: " + location + ".\tInventory: " + inventory;
    }

}