package com.tlg.view;

public class DisplayText extends Display{
    private final static int MAX_LINES = 8;
    private String display;

    public DisplayText() {
        super(MAX_LINES);
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}