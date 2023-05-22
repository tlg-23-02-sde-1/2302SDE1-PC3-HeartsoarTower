package com.tlg.heartsoar;

import java.util.ArrayList;
import java.util.List;

class Player {
    private String name = "Harmony";
    private Room location;
    private Room prevLocation;
    private List<Item> inventory = new ArrayList<>();
    private Map map;


    Player(List<Room> rooms) {
//        Set the location with the name Entrance
        for (Room room : rooms){
            if (room.getName().equals("Entrance")){
                setLocation(room);
                break;
            }
        }

    }
    public Room getLocation() {
        return location;
    }

    public void setLocation(Room currentLocation) {
        this.location = currentLocation;
    }

    public Room getPrevLocation() {
        return prevLocation;
    }

    public void setPrevLocation(Room prevLocation) {
        this.prevLocation = prevLocation;
    }

    public List<Item> getInventory() {
        return inventory;
    }

}
