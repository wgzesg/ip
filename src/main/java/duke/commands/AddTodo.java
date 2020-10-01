package duke.commands;

import duke.storage.Database;

/**
 * Command AddTodo can be executed through calling this.
 */
public class AddTodo extends Command {
    @Override
    public int execute() {
        Database.addToDo(args);
        clearArgs();
        return 0;
    }
}
