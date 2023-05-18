package com.tlg.heartsoar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.tlg.language.TextParser;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class Factory {
    Collection<String[]> VERBS;
    Collection<String[]> NOUNS;

    //    private TextParser textParser = new TextParser(VERBS, NOUNS);
    private List<Room> rooms = new ArrayList<>();
    //    Read from entire JSON folder
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    Path roomFolder = Paths.get("src/main/resources/Rooms");

    public void populateRooms() throws IOException {
        try {
            DirectoryStream<Path> roomStream = Files.newDirectoryStream(roomFolder);
            for (Path el : roomStream) {
                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
                Room room = gson.fromJson(jsonElement, Room.class);
                rooms.add(room);
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    private void populateVerbs() throws IOException {
//        Make a list of verbs with their synonyms from the JSON file
        try {
            Path verbFile = Paths.get("src/main/resources/Words/VerbList.json");
            JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(verbFile), JsonElement.class);
            VERBS = gson.fromJson(jsonElement, Collection.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateNouns() throws IOException {
        //        Make a list of verbs with their synonyms from the JSON file
        try {
            Path nounFile = Paths.get("src/main/resources/Words/VerbList.json");
            JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(nounFile), JsonElement.class);
            NOUNS = gson.fromJson(jsonElement, Collection.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Factory() throws IOException {
        populateRooms();
        populateVerbs();
        populateNouns();
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public Collection<String[]> getVerbs() {
        return VERBS;
    }
    public Collection<String[]> getNouns() {
        return NOUNS;
    }
}