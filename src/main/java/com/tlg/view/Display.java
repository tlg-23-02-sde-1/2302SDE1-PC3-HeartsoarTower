package com.tlg.view;

import java.util.ArrayList;
import java.util.List;

class Display {
    public static int MAX_LINES;
    public static String display;

    Display(int MAX_LINES) {
        this.MAX_LINES = MAX_LINES;
    }


    public String getDisplay(String display) {
        List<String> lines = new ArrayList<>();
        lines = List.of(display.split("\r\n|\r|\n"));
        while(lines.size() < MAX_LINES) {
            lines.add(0, "\n");
            if (lines.size() < MAX_LINES) {
                lines.add("\n");
            }
        }
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (String line : lines) {
            if (counter == MAX_LINES) break;
            sb.append(line);
            sb.append("\n");
            counter++;
        }
        return sb.toString();
    }
}