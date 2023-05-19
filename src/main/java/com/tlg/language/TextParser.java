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

    public TextParser(TreeMap<String, ArrayList<String>> verbs, TreeMap<String, ArrayList<String>> nouns) {
        NOUNS = nouns;
        VERBS = verbs;
    }

    public String[] validCombo(String input){
        String verb = null;
        String noun = null;

//        Split input into words.  Strip out punctuation. Convert to lower case.
        input = input.replaceAll("[^a-zA-Z ]", "").toLowerCase();
        String[] words = input.split(" ");
//        Check each word in words to see if it is a verb or noun.
        for (String word : words) {
            for (Map.Entry<String, ArrayList<String>> entry : VERBS.entrySet()) {
                if (entry.getValue().contains(word)) {
                    verb = entry.getKey();
                    break;
                }
            }
            for (Map.Entry<String, ArrayList<String>> entry : NOUNS.entrySet()) {
                if (entry.getValue().contains(word)) {
                    noun = entry.getKey();
                    break;
                }
            }
        }
        return new String[]{verb, noun};
    }





}