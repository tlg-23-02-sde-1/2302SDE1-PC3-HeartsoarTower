package com.tlg.language;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;



public class TextParser {
    //    TODO: Make singleton
    private final List<String> NOUNS;
    private final List<String> VERBS;

    //    TODO: Read nouns and verbs from JSON file
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    String jsonFile = "src/main/resources/language.json";




    public TextParser(List<String> noun, List<String> verbs) {
        NOUNS = noun;
        VERBS = verbs;
    }

    private String[] validCombo(String input){
        String verb = null;
        String noun = null;
        // Split the input by spaces.  Check for a verb.  Check for a noun.
        String[] words = input.split(" ");
        for (String word : words) {
            if (VERBS.contains(word)) {
                verb = word;
            }
            if (NOUNS.contains(word)) {
                noun = word;
            }
        }
        if (verb == null || noun == null) {
            return null;
        }
        return new String[]{verb, noun};
    }



}