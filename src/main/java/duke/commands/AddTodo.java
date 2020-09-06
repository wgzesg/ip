package duke.commands;

import duke.core.Database;
import duke.exceptions.NullArgumentException;

public class AddTodo extends Command {
    @Override
    public int execute() {
        Database.addToDo(args);
        clearArgs();
        return 0;
    }
}
