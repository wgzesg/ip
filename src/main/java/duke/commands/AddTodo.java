package duke.commands;

import duke.storage.Storage;

/**
 * Command AddTodo can be executed through calling this.
 */
public class AddTodo extends Command {
    @Override
    public int execute() {
        Storage.addToDo(args);
        clearArgs();
        return 0;
    }
}
