package com.tlg.view;

public class DisplayInput extends Display{
    private final static int MAX_LINES = 1;
    private String display;

    public DisplayInput() {
        super(MAX_LINES);
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

}