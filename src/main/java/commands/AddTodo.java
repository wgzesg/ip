package commands;

import core.Database;

public class AddTodo extends Command {
    @Override
    public int execute() {
        Database.addToDo(args);
        return 0;
    }
}
