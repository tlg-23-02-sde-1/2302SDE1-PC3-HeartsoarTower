package com.tlg.heartsoar;

import java.util.List;
import java.util.Scanner;

class HeartsoarTower {
//    TODO: Remove nouns and verbs from here once JSON implemented
    List<String> NOUNS;
    List<String> VERBS;
    private TextParser textParser = new TextParser(VERBS, NOUNS);

//    TODO: Place in proper location once game loop established
    // Take input from the user via the console:
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] instruct = textParser.validCombo(input);

}
