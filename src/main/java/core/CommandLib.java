package core;

import commands.*;
import exceptions.NullArgumentException;

import java.awt.desktop.SystemEventListener;
import java.util.HashMap;

public class CommandLib {
    private final HashMap<String, Command> commandMap = new HashMap<>();

    public CommandLib() {
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

        // Catch unrecognised command and
        try {
            command.args = args[1];
            System.out.println(command.args);
        } catch (NullPointerException e) {
            System.out.println("Oops, the command is not recognised!");
            return 0;
        } catch (ArrayIndexOutOfBoundsException e) {}

        int result = 0;
        try {
            result = command.execute();
        } catch (NullArgumentException e){
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Oops, the command is not recognised!");
        }

        return result;
    }
}


