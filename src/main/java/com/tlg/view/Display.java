package com.tlg.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Display {
    public static int MAX_LINES;
    public static String display;

    Display(int MAX_LINES) {
        this.MAX_LINES = MAX_LINES;
    }


    public String trimDisplay(String display, int MAX_LINES) {
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(display.split("\r\n|\r|\n"));
        List<String> lines = Stream.of(display.split("\r\n|\r|\n")).collect(Collectors.toList());
        while(lines.size() < MAX_LINES) {
            lines.add(0, " ");
            if (lines.size() < MAX_LINES) {
                lines.add(" ");
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