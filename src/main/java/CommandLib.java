import java.util.HashMap;

public class CommandLib {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public CommandLib(){
        MarkDone markDone = new MarkDone();
        this.register("done", markDone);
        Add add = new Add();
        this.register("add", add);
        ListAll listAll = new ListAll();
        this.register("list", listAll);
        PrintBye printBye = new PrintBye();
        this.register("bye", printBye);
    }

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public int execute(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            command = commandMap.get("add");
        }
        command.args = args;
        return command.execute();
    }
}

class MarkDone extends Command{

    @Override
    public int execute() {
        ActionLib.markDone(args[1]);
        return 0;
    }
}

class PrintBye extends Command{

    @Override
    public int execute() {
        ActionLib.printBye();
        return -1;
    }
}

class ListAll extends Command{

    @Override
    public int execute() {
        ActionLib.listAll();
        return 0;
    }
}

class Add extends Command{

    @Override
    public int execute() {
        ActionLib.addToTodoList(args);
        return 0;
    }
}


