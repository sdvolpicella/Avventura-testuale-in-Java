package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Container extends AdvObject implements Serializable {

    private List<AdvObject> list = new ArrayList<>();

    public Container(int id, String name, Set<String> alias, String description, boolean isOpenable, boolean opened, boolean isContainer) {
        super(id, name, alias, description, isOpenable, opened, isContainer);
    }

    public Container(int id, String name, String description) {
        super(id, name, description);
    }

    public List<AdvObject> getList() {
        return list;
    }

    public void setList(List<AdvObject> list) {
        this.list = list;
    }

    public void addObject(AdvObject obj) {
        list.add(obj);
    }

    public void removeObject(AdvObject obj) {
        list.remove(obj);
    }

}
