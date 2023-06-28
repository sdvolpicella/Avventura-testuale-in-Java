package parser;

import entity.AdvObject;
import utils.Utils;
import entity.Command;
import java.util.Set;
import java.util.List;

public class Parser {

    private final Set<String> stopwords;

    public Parser(Set<String> stopwords) {
        this.stopwords = stopwords;
    }

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {

            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {

                return i;

            }
        }
        return -1;
    }

    private int checkForObject(String token, List<AdvObject> objects) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getName().equals(token) || objects.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {

        List<String> tokens = Utils.obtainTokens(command, stopwords);
        int i_command = -1;
        int i_objRoom = -1;
        int i_objInventory = -1;

        if (!tokens.isEmpty()) {
            i_command = checkForCommand(tokens.get(0), commands);

            if (i_command != -1) {

                if (tokens.size() > 1) {
                    if ((i_objRoom = checkForObject(tokens.get(1), objects)) != -1) {
                        return new ParserOutput(commands.get(i_command), objects.get(i_objRoom));
                    } else if ((i_objInventory = checkForObject(tokens.get(1), inventory)) != -1) {
                        return new ParserOutput(commands.get(i_command), inventory.get(i_objInventory));
                    } else {
                        return new ParserOutput(commands.get(i_command));
                    }

                } else {
                    return new ParserOutput(commands.get(i_command));
                }
            } else {
                return new ParserOutput(null);
            }
        } else {
            return null;
        }

    }

}
