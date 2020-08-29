import java.util.HashMap;

public class CommandLib {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public CommandLib(){
        // Initialise the map with all possible commands
        MarkDone markDone = new MarkDone();
        this.register("done", markDone);
        AddTodo addTodo = new AddTodo();
        this.register("todo", addTodo);
        AddDeadline addDeadline = new AddDeadline();
        this.register("deadline", addDeadline);
        AddEvent addEvent = new AddEvent();
        this.register("event", addEvent);
        ListAll listAll = new ListAll();
        this.register("list", listAll);
        PrintBye printBye = new PrintBye();
        this.register("bye", printBye);
    }

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public int execute(String cmd) {

        String[] args = cmd.split(" ", 2);
        Command command = commandMap.get(args[0]);
        if (command == null) {
            command = commandMap.get("add");
        }
        if(command == null){
            System.out.println("Command is not recognised!");
            return 0;
        }
        if(args.length > 1) {
            command.args = args[1];
        }
        return command.execute();
    }
}

class MarkDone extends Command{
    @Override
    public int execute() {
        ActionLib.markDone(args);
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

class AddTodo extends Command{
    @Override
    public int execute() {
        ActionLib.addToDo(args);
        return 0;
    }
}

class AddDeadline extends Command{
    @Override
    public int execute() {
        ActionLib.addDeadline(args);
        return 0;
    }
}

class AddEvent extends Command{
    @Override
    public int execute() {
        ActionLib.addEvent(args);
        return 0;
    }
}


