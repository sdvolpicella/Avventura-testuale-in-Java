package gameEngine;

import parser.ParserOutput;
import entity.AdvObject;
import entity.Command;
import entity.Room;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public abstract class GameDescription implements Serializable {

    private final List<Room> rooms = new ArrayList<>();

    private final List<Command> commands = new ArrayList<>();

    private final List<AdvObject> gameObjects = new ArrayList<>();

    private final List<AdvObject> inventory = new ArrayList<>();

    private Room currentRoom;

    public List<Room> getRooms() {
        return rooms;
    }

    public List<AdvObject> getInventory() {
        return inventory;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public List<AdvObject> getGameObjects() {
        return gameObjects;
    }

    public abstract void init() throws Exception;

    public abstract void nextMove(ParserOutput p);

}
