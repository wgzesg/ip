package commands;

import core.Database;
import exceptions.NullArgumentException;

public class AddTodo extends Command {
    @Override
    public int execute() {
        if(args == null || args.isBlank()){
            throw new NullArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Database.addToDo(args);
        return 0;
    }
}
