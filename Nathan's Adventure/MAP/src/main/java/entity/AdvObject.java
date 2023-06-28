package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class AdvObject implements Serializable {

    private final int id;

    private String name;

    private Set<String> alias; //struttura dati che contiene  i sinonimi del nome dell'oggetto.

    private String description;

    private boolean pickable;

    private boolean usable;

    private boolean pullable;

    private boolean isWeapon;

    private boolean isLeaveable;

    private boolean isBreakable;

    private boolean isReadable;

    private boolean isOpenable;

    private int damage;

    private int pointsWhenPicked;

    private String textOfItem;

    private boolean isBroken;

    private boolean isPulled;

    private boolean picked;

    private String author;

    private boolean opened;

    private boolean inside;

    private boolean isContained;

    private String ContainedIn;

    private boolean isContainer;

    private boolean isActivated;

    
    
    public AdvObject(int id, String name, Set<String> alias, String description, boolean pickable, boolean usable,
            boolean pullable, boolean isWeapon, boolean isLeaveable, boolean isBreakable, boolean isReadable, int damage,
            int pointsWhenPicked, String textOfItem, boolean isOpenable, boolean isBroken, boolean isPulled, String author,
            boolean picked, boolean opened, boolean inside, boolean isContained, String containedIn, boolean isContainer,
            boolean isActivated) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.pickable = pickable;
        this.usable = usable;
        this.pullable = pullable;
        this.isWeapon = isWeapon;
        this.isLeaveable = isLeaveable;
        this.isBreakable = isBreakable;
        this.isReadable = isReadable;
        this.damage = damage;
        this.pointsWhenPicked = pointsWhenPicked;
        this.textOfItem = textOfItem;
        this.isOpenable = isOpenable;
        this.isBroken = isBroken;
        this.isPulled = isPulled;
        this.author = author;
        this.picked = picked;
        this.opened = opened;
        this.inside = inside;
        this.isContained = isContained;
        this.ContainedIn = containedIn;
        this.isContainer = isContainer;
        this.isActivated = isActivated;
    }

    public AdvObject(int id, String name, Set<String> alias, String description, boolean isOpenable, boolean opened, boolean isContainer) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.isOpenable = isOpenable;
        this.opened = opened;
        this.isContainer = isContainer;
    }

    public AdvObject(int id, String name, Set<String> alias, String description) {
        super();
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.description = description;
    }

    public AdvObject(int id, String name, String description) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setIsActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public boolean isContainer() {
        return isContainer;
    }

    public void setIsContainer(boolean isContainer) {
        this.isContainer = isContainer;
    }

    public boolean isContained() {
        return isContained;
    }

    public void setIsContained(boolean isContained) {
        this.isContained = isContained;
    }

    public String getContainedIn() {
        return ContainedIn;
    }

    public void setContainedIn(String ContainedIn) {
        this.ContainedIn = ContainedIn;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean inside) {
        this.inside = inside;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isPulled() {
        return isPulled;
    }

    public void setPulled(boolean isPulled) {
        this.isPulled = isPulled;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean isBroken) {
        this.isBroken = isBroken;
    }

    public boolean isOpenable() {
        return isOpenable;
    }

    public void setOpenable(boolean isOpenable) {
        this.isOpenable = isOpenable;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getTextOfItem() {
        return textOfItem;
    }

    public void setTextOfItem(String textOfItem) {
        this.textOfItem = textOfItem;
    }

    public boolean isPickable() {
        return pickable;
    }

    public void setPickable(boolean pickable) {
        this.pickable = pickable;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public boolean isPullable() {
        return pullable;
    }

    public void setPullable(boolean pullable) {
        this.pullable = pullable;
    }

    public boolean isWeapon() {
        return isWeapon;
    }

    public void setWeapon(boolean isWeapon) {
        this.isWeapon = isWeapon;
    }

    public boolean isLeaveable() {
        return isLeaveable;
    }

    public void setLeaveable(boolean isLeaveable) {
        this.isLeaveable = isLeaveable;
    }

    public boolean isBreakable() {
        return isBreakable;
    }

    public void setBreakable(boolean isBreakable) {
        this.isBreakable = isBreakable;
    }

    public boolean isReadable() {
        return isReadable;
    }

    public void setReadable(boolean isReadable) {
        this.isReadable = isReadable;
    }

    public int getDamge() {
        return damage;
    }

    public void setDamge(int damge) {
        this.damage = damge;
    }

    public int getPointsWhenPicked() {
        return pointsWhenPicked;
    }

    public void setPointsWhenPicked(int pointsWhenPicked) {
        this.pointsWhenPicked = pointsWhenPicked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
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
        AdvObject other = (AdvObject) obj;
        return id == other.id;
    }

}
