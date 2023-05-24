package com.tlg.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Factory {
    TreeMap<String, ArrayList<String>> VERBS;
    TreeMap<String, ArrayList<String>> NOUNS;
    private List<Room> rooms = new ArrayList<>();
    private List<Monster> monsters = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<Scene> scenes = new ArrayList<>();

    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    //    Read from entire JSON folder
//    final String basePath = "src/main/resources/";
//    Path roomFolder = Paths.get(basePath+"Rooms");
//    Path monsterFolder = Paths.get(basePath+"Monsters");
//    Path itemFolder = Paths.get(basePath+"Items");
//    Path sceneFolder = Paths.get(basePath + "Scenes");
    private static final String ROOMS_PATH = "Rooms/rooms.json";
    private static final String ITEMS_PATH = "Items/items.json";
    private static final String MONSTERS_PATH = "Monsters/monsters.json";
    private static final String SCENES_PATH = "Scenes/scenes.json";
    private static final String VERBS_PATH = "Words/Verbs.json";
    private static final String NOUNS_PATH = "Words/Nouns.json";

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
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(ROOMS_PATH);
            Reader rdr = new InputStreamReader(is);
        ) {
            rooms = gson.fromJson(rdr, new TypeToken<List<Room>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void populateMonsters() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(MONSTERS_PATH);
            Reader rdr = new InputStreamReader(is);
        ) {
            monsters = gson.fromJson(rdr, new TypeToken<List<Monster>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void populateItems() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(ITEMS_PATH);
            Reader rdr = new InputStreamReader(is);
        ) {
            items = gson.fromJson(rdr, new TypeToken<List<Item>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateScenes() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(SCENES_PATH);
            Reader rdr = new InputStreamReader(is);
        ) {
            List<SceneBuilder> sceneBuilder = gson.fromJson(rdr, new TypeToken<List<SceneBuilder>>(){}.getType());
            for (SceneBuilder e : sceneBuilder){
                Scene scene = new Scene(e, rooms, items, monsters);
                scenes.add(scene);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void populateVerbs() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(VERBS_PATH);
            Reader rdr = new InputStreamReader(is);
        ) {
            VERBS = gson.fromJson(rdr, new TypeToken<TreeMap<String, ArrayList<String>>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void populateNouns() throws IOException {
        try(InputStream is = getClass().getClassLoader().getResourceAsStream(NOUNS_PATH);
            Reader rdr = new InputStreamReader(is);
        ) {
            NOUNS = gson.fromJson(rdr, new TypeToken<TreeMap<String, ArrayList<String>>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void populateMonsters() throws IOException {
//        try {
//            DirectoryStream<Path> monsterStream = Files.newDirectoryStream(monsterFolder);
//            for (Path el : monsterStream) {
//                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
//                Monster monster = gson.fromJson(jsonElement, Monster.class);
//                monsters.add(monster);
//            }
//            ;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void populateItems() throws IOException {
//        try {
//            DirectoryStream<Path> itemStream = Files.newDirectoryStream(itemFolder);
//            for (Path el : itemStream) {
//                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
//                Item item = gson.fromJson(jsonElement, Item.class);
//                items.add(item);
//            }
//            ;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    private void populateVerbs() throws IOException {
////        Make a list of verbs with their synonyms from the JSON file
//        try {
//            Path verbFile = Paths.get("src/main/resources/Words/Verbs.json");
//            JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(verbFile), JsonElement.class);
//            VERBS = gson.fromJson(jsonElement, TreeMap.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private void populateNouns() throws IOException {
//        //        Make a list of verbs with their synonyms from the JSON file
//        try {
//            Path nounFile = Paths.get("src/main/resources/Words/Nouns.json");
//            JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(nounFile), JsonElement.class);
//            NOUNS = gson.fromJson(jsonElement, TreeMap.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void populateScenes() throws IOException {
//        try {
//            DirectoryStream<Path> sceneStream = Files.newDirectoryStream(sceneFolder);
//            for (Path el : sceneStream) {
//                JsonElement jsonElement = gson.fromJson(Files.newBufferedReader(el), JsonElement.class);
//                SceneBuilder sceneBuilder = gson.fromJson(jsonElement, SceneBuilder.class);
//                Scene scene = new Scene(sceneBuilder, rooms, items, monsters);
//                scenes.add(scene);
//
//
//            }
//            ;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public Factory() throws IOException {
        populateRooms();
        populateItems();
        populateMonsters();
        populateVerbs();
        populateNouns();
        populateScenes();
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

    public List<Scene> getScenes() {
        return scenes;
    }
}