package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import battle.Combattimento;

public class Room implements Serializable {

    private final int id; 

    private String name; 

    private boolean isVisited; 

    private String descriptionUnseen;

    private String descriptionSeen;

    private String look;

    private String lookEventComplete; 

    private boolean eventComplete = false; 

    private boolean isDark; 

    private boolean isLocked;

    private Room north = null; 
    
    private Room south = null;

    private Room east = null;

    private Room west = null;

    private transient Combattimento battle = null;

    private MultipleChoiceDialog dialog = null;

    private List<AdvObject> objects = new ArrayList<>();
    
    
    public Room(int id, String name, boolean isVisited, String descriptionUnseen, String descriptionSeen, String look,
            String lookEventComplete, boolean eventComplete, boolean isDark, boolean isLocked) {
        super();
        this.id = id;
        this.name = name;
        this.isVisited = isVisited;
        this.descriptionUnseen = descriptionUnseen;
        this.descriptionSeen = descriptionSeen;
        this.look = look;
        this.lookEventComplete = lookEventComplete;
        this.eventComplete = eventComplete;
        this.isDark = isDark;
        this.isLocked = isLocked;

    }

    public void setObjects(List<AdvObject> objects) {
        this.objects = objects;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean isIsDark() {
        return isDark;
    }

    public void setIsDark(boolean isDark) {
        this.isDark = isDark;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public Combattimento getBattle() {
        return battle;
    }

    public void setBattle(Combattimento battle) {
        this.battle = battle;
    }

    public MultipleChoiceDialog getDialog() {
        return dialog;
    }

    public void setDialog(MultipleChoiceDialog dialog) {
        this.dialog = dialog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        this.isVisited = visited;
    }

    public String getDescriptionUnseen() {
        return descriptionUnseen;
    }

    public void setDescriptionUnseen(String descriptionUnseen) {
        this.descriptionUnseen = descriptionUnseen;
    }

    public String getDescriptionSeen() {
        return descriptionSeen;
    }

    public void setDescriptionSeen(String descriptionSeen) {
        this.descriptionSeen = descriptionSeen;
    }

    public String getLook() {
        return look;
    }

    public void setLook(String look) {
        this.look = look;
    }

    public boolean isDark() {
        return isDark;
    }

    public void setDark(boolean dark) {
        this.isDark = dark;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
    }

    public Room getSouth() {
        return south;
    }

    public void setSouth(Room south) {
        this.south = south;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
    }

    public int getId() {
        return id;
    }

    public String getLookEventComplete() {
        return lookEventComplete;
    }

    public void setLookEventComplete(String lookEventComplete) {
        this.lookEventComplete = lookEventComplete;
    }

    public boolean isEventComplete() {
        return eventComplete;
    }

    public void setEventComplete(boolean eventComplete) {
        this.eventComplete = eventComplete;
    }

    public List<AdvObject> getObjects() {
        return objects;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Room other = (Room) obj;
        return id == other.id;
    }
}
