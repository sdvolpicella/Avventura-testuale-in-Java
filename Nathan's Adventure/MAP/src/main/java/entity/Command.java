package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;


public class Command implements Serializable {

    private final CommandType type;

    private final String name;

    private Set<String> alias;

    public Command(CommandType type, String name) {
        this.type = type;
        this.name = name;
    }

    public Command(CommandType type, String name, Set<String> alias) {
        this.type = type;
        this.name = name;
        this.alias = alias;
    }

    public Set<String> getAlias() {
        return alias;
    }

    public void setAlias(Set<String> alias) {
        this.alias = alias;
    }

    public void setAlias(String[] alias) {
        this.alias = new HashSet<>(Arrays.asList(alias));
    }

    public CommandType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
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
        Command other = (Command) obj;
        return type == other.type;
    }

}
