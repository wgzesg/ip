import java.util.HashMap;

public class CommandLib {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public CommandLib(){
        // Initialise the map with all possible commands
        MarkDone markDone = new MarkDone();
        this.register(Constants.DONE_CMD, markDone);
        AddTodo addTodo = new AddTodo();
        this.register(Constants.TODO_CMD, addTodo);
        AddDeadline addDeadline = new AddDeadline();
        this.register(Constants.DEADLINE_CMD, addDeadline);
        AddEvent addEvent = new AddEvent();
        this.register(Constants.EVENT_CMD, addEvent);
        ListAll listAll = new ListAll();
        this.register(Constants.LIST_CMD, listAll);
        PrintBye printBye = new PrintBye();
        this.register(Constants.BYE_CMD, printBye);
    }

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public int execute(String cmd) {

        String[] args = cmd.split(" ", 2);
        Command command = commandMap.get(args[0]);
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

abstract class Command{
    public String args;

    public int execute() {
        return 0;
    }
}

class MarkDone extends Command{
    @Override
    public int execute() {
        Database.markDone(args);
        return 0;
    }
}

class PrintBye extends Command{
    @Override
    public int execute() {
        Database.printBye();
        return -1;
    }
}

class ListAll extends Command{
    @Override
    public int execute() {
        Database.listAll();
        return 0;
    }
}

class AddTodo extends Command{
    @Override
    public int execute() {
        Database.addToDo(args);
        return 0;
    }
}

class AddDeadline extends Command{
    @Override
    public int execute() {
        Database.addDeadline(args);
        return 0;
    }
}

class AddEvent extends Command{
    @Override
    public int execute() {
        Database.addEvent(args);
        return 0;
    }
}


