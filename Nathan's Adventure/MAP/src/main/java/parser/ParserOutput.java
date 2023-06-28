package parser;

import entity.Command;
import entity.AdvObject;

public class ParserOutput {

    private Command command;

    private AdvObject Obj;

    public ParserOutput(Command command, AdvObject Obj) {
        super();
        this.command = command;
        this.Obj = Obj;
    }

    public ParserOutput(Command command) {
        super();
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public AdvObject getObj() {
        return Obj;
    }

    public void setObj(AdvObject obj) {
        Obj = obj;
    }

}
