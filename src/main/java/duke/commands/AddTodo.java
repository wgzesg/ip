package duke.commands;

import duke.storage.Database;

public class AddTodo extends Command {
    @Override
    public int execute() {
        Database.addToDo(args);
        clearArgs();
        return 0;
    }
}
