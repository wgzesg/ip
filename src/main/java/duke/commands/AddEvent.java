package duke.commands;

import duke.storage.Database;

/**
 * Command AddEvent can be executed through calling this.
 */
public class AddEvent extends Command {
    @Override
    public int execute() {
        Database.addEvent(args);
        clearArgs();
        return 0;
    }
}
