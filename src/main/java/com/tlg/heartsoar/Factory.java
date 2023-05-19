package com.tlg.heartsoar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;
import com.tlg.language.TextParser;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

class Factory {
    TreeMap<String, ArrayList<String>> VERBS;
    TreeMap<String, ArrayList<String>> NOUNS;
    private List<Room> rooms = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    //    Read from entire JSON folder
    final String basePath = "src/main/resources/";
    Path roomFolder = Paths.get(basePath+"Rooms");
    Path monsterFolder = Paths.get(basePath+"Monsters");
    Path itemFolder = Paths.get(basePath+"Items");

//    public <E> void populate(List<E> listBuilder, Path folder, Object subject) throws IOException {
//        try {
//            DirectoryStream<Path> stream = Files.newDirectoryStream(folder);
////            Find out what kind of subject we are working with:
//            System.out.println(subject+"\n"+subject.getClass()+"\n"+subject.getClass().getSimpleName());
//
//
//            for (Path el : stream) {
//                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
//                subject appending = gson.fromJson(jsonElement, E.class);
//                listBuilder.add(appending);
//            }
//            ;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

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

    public void populateMonsters() throws IOException {
        try {
            DirectoryStream<Path> monsterStream = Files.newDirectoryStream(monsterFolder);
            for (Path el : monsterStream) {
                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
                Monster monster = gson.fromJson(jsonElement, Monster.class);
                monsters.add(monster);
            }
            ;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void populateItems() throws IOException {
        try {
            DirectoryStream<Path> itemStream = Files.newDirectoryStream(itemFolder);
            for (Path el : itemStream) {
                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
                Item item = gson.fromJson(jsonElement, Item.class);
                items.add(item);
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
            VERBS = gson.fromJson(jsonElement, TreeMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateNouns() throws IOException {
        //        Make a list of verbs with their synonyms from the JSON file
        try {
            Path nounFile = Paths.get("src/main/resources/Words/VerbList.json");
            JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(nounFile), JsonElement.class);
            NOUNS = gson.fromJson(jsonElement, TreeMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Factory() throws IOException {
        populateRooms();
        populateItems();
        populateMonsters();
        populateVerbs();
        populateNouns();
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public TreeMap<String, ArrayList<String>> getVerbs() {
        return VERBS;
    }
    public TreeMap<String, ArrayList<String>> getNouns() {
        return NOUNS;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public List<Item> getItems() {
        return items;
    }
}