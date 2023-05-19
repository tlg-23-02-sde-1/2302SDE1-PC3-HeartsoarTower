package com.tlg.language;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class TextParser {
    private final TreeMap<String, ArrayList<String>> VERBS;
    private final TreeMap<String, ArrayList<String>> NOUNS;

    public TextParser(TreeMap<String, ArrayList<String>> nouns, TreeMap<String, ArrayList<String>> verbs) {
        NOUNS = nouns;
        VERBS = verbs;
    }

    private String[] validCombo(String input){
        String verb = null;
        String noun = null;

        for (Map.Entry<String, ArrayList<String>> entry : VERBS.entrySet()) {
            if (input.contains(entry.getKey())) {
                verb = entry.getKey();
                break;
            }
        }
        for (Map.Entry<String, ArrayList<String>> entry : NOUNS.entrySet()) {
            if (input.contains(entry.getKey())) {
                noun = entry.getKey();
                break;
            }
        }
        if (verb == null || noun == null) {
            return null;
        }
        return new String[]{verb, noun};
    }



}